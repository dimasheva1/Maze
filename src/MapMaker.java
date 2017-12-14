
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;


public class MapMaker {

	
    private static final int  UNDETERMINED = -2;  //непонятные
    private static final int  SET_WALL = -1;
    private ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("bricks.png")));
    private ImageIcon img1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("planks.png")));

    private int       rows;           //строки    
    private int       cols;           //столбцы

    private int       act_rows;       //Текущий номер строки
    private int       act_cols;       //Текущий номер столбца

    private Cell[][]  feild;          //Поле лабиринта

    private int[]     current;        //текущая строка, за исключением наружных стен
    private int[]     next;           //следующая строка, за исключением наружных стен

    private int       numSet;         //Проверка на совпадение
        private Random fRand;
        private int fNext;
        private int fNext2;

    public static void main(String[]args)
    {
    	MapMaker a = new MapMaker(10,10);
    	a.makeMaze();
    
    	
    }
        
        
        
        
        
        
        

    /* конструктор */
    public MapMaker (int nRows, int nCols)
    {
        act_rows = nRows;
        act_cols = nCols;

        rows = act_rows*2+1;
        cols = act_cols*2+1;

        feild   = new Cell[rows][cols];
        current = new int[act_cols*2-1];
        next    = new int[act_cols*2-1];


        /* Sets the maze to filled */
        for(int i =0; i<feild[0].length; i++){
            for(int j=0; j<feild.length; j++){
                feild[i][j] = new Wall(i,j,img);
            }
        }


        for(int i=0; i<next.length; i++){
            next[i] = UNDETERMINED;
        }



        /* Инициализация первой строки */
        for(int i=0; i<current.length; i+=2){
            current[i] = i/2+1;
            if(i != current.length-1)
                current[i+1] = SET_WALL;
        }
        numSet = current[current.length-1];
    }


    public void makeMaze()
    {

        setRand(new Random());


        for(int q=0; q<act_rows-1; q++){   //для всех строк кроме последней

            if(q != 0){

                /* получим текущую строку из последней итерации*/
                for(int i=0; i<current.length; i++){
                    current[i] = next[i];
                    next[i] = UNDETERMINED;
                }
            }


            joinSets();
            makeVerticalCuts();


            /* заполним остальную часть следующей строки */

            for(int j=0; j<current.length; j+=2){

                if(next[j] == UNDETERMINED)
                    next[j] = ++numSet;

                if(j != current.length-1)
                    next[j+1] = SET_WALL;
            }


            /* запишем текущую строку в поле */
            for(int k=0; k<current.length; k++){

                if(current[k] == SET_WALL){
                    feild[2*q+1][k+1] = new Wall(2*q+1,k+1,img);
                    feild[2*q+2][k+1] = new Wall(2*q+2,k+1,img);
                }else{
                    feild[2*q+1][k+1] = new Path(2*q+1,k+1,new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("planks.png"))));

                    if(current[k] == next[k]){
                        feild[2*q+2][k+1] = new Path(2*q+2,k+1,new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("planks.png"))));
                    }
                }

            }

        }

        makeLastRow();
        makeOpenings();
        

    }

    private void joinSets()
    {
        Random rand = new Random();

        /* Случайные наборы */
        for(int i=1; i<current.length-1; i+=2){ //проверка только где стены

            /* Проверка на объединение:
             *      Имеют ли стену между ними,
             *      не являются ли частью одного набора
             *Получаем случайный набор, при объединении
             */
            if(current[i] == SET_WALL && current[i-1] != current[i+1]
                    && rand.nextBoolean()){


                current[i] = 0; //Убрать стену

                int old  = Math.max(current[i-1],current[i+1]);
                fNext= Math.min(current[i-1],current[i+1]);

                // Объединяем два набора в один
                  
                 
                for(int j=0; j<current.length; j++){

                    if(current[j] == old)
                        current[j] = fNext;
                }
                    }
        }
    }


    /* Случайно выбрать вертикальные пути для каждого набора, убедившись, 
     * что есть по крайней мере 1 вертикальный путь в наборе
     */
    private void makeVerticalCuts()
    {
        Random   rand          = new Random();

        int      begining;     //Начало набора(Включительно)
        int      end;          //Конец набор(Включительно)

        boolean madeVertical;  //Создание вертикального прохода
                                

        int i;
        begining = 0;
        do{

            /* Поиск конца */
            i=begining;
            while(i<current.length-1 && current[i] == current[i+2]){
                i+=2;
            }
            end = i;

            //Наличие петли 
              
             
            madeVertical = false;
            do{
                for(int j=begining; j<=end; j+=2){

                    if(rand.nextBoolean()){
                        next[j] = current[j];
                        madeVertical = true;
                    }
                }
            }while(!madeVertical);

            begining = end+2;  //перейти к следующему набору в строке

        }while(end != current.length-1);
    }




    private void makeLastRow()
    {

        /* Получение текущей строки */
        for(int i=0; i<current.length; i++){
            current[i] = next[i];
        }

        for(int i=1; i<current.length-1; i+=2){

            if(current[i] == SET_WALL && current[i-1] != current[i+1]){

                current[i] = 0;

                int old  = Math.max(current[i-1],current[i+1]);
                fNext2= Math.min(current[i-1],current[i+1]);

                // Объединяем два набора в один
                for(int j=0; j<current.length; j++){

                    if(current[j] == old)
                        current[j] = fNext2;
                }
            }
        }


        /* Добавление последней строки */
        for(int k=0; k<current.length; k++){

            if(current[k] == SET_WALL){
                feild[rows-2][k+1] = new Wall(rows-2,k+1,img);
            }else{
                feild[rows-2][k+1] = new Path(rows-2,k+1,img1);
            }
        }

    }

    public void makeOpenings(){

        Random rand = new Random(); //два различных генераторов случайных чисел
        Random rand2 = new Random();//на всякий случай

        //случайное место для входа и выхода
        int entrance_row = rand.nextInt(act_rows-1) * 2 +1;
        int exit_row = rand2.nextInt(act_rows-1) * 2 +1;
        do {
        	if (feild[entrance_row][1].getGoTo())
        		feild[entrance_row][0] = new Path(entrance_row,0,img1);
        	else entrance_row = rand.nextInt(act_rows-1) * 2 +1;
        }
        while(feild[entrance_row][1].getGoTo()!=true);
        	do {
        	if (feild[exit_row][cols-2].getGoTo())
        		feild[exit_row][cols-1] = new Path(exit_row,cols-1,img1);
            else entrance_row = rand.nextInt(act_rows-1) * 2 +1;
        	}
                while(feild[exit_row][cols-2].getGoTo()!=true);
        

    }
    

    
    public Cell[][] getMaze()
    {
        return feild;
    }


        public Random getRand() {
                return fRand;
        }


        public void setRand(Random rand) {
                fRand = rand;
        }
        
        public int getCols()
        {
        	return cols;
        }

        public int getRows()
        {
        	return rows;
        }
}
