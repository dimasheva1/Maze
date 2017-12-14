
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;


public class MapMaker {

	
    private static final int  UNDETERMINED = -2;  //����������
    private static final int  SET_WALL = -1;
    private ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("bricks.png")));
    private ImageIcon img1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("planks.png")));

    private int       rows;           //������    
    private int       cols;           //�������

    private int       act_rows;       //������� ����� ������
    private int       act_cols;       //������� ����� �������

    private Cell[][]  feild;          //���� ���������

    private int[]     current;        //������� ������, �� ����������� �������� ����
    private int[]     next;           //��������� ������, �� ����������� �������� ����

    private int       numSet;         //�������� �� ����������
        private Random fRand;
        private int fNext;
        private int fNext2;

    public static void main(String[]args)
    {
    	MapMaker a = new MapMaker(10,10);
    	a.makeMaze();
    
    	
    }
        
        
        
        
        
        
        

    /* ����������� */
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



        /* ������������� ������ ������ */
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


        for(int q=0; q<act_rows-1; q++){   //��� ���� ����� ����� ���������

            if(q != 0){

                /* ������� ������� ������ �� ��������� ��������*/
                for(int i=0; i<current.length; i++){
                    current[i] = next[i];
                    next[i] = UNDETERMINED;
                }
            }


            joinSets();
            makeVerticalCuts();


            /* �������� ��������� ����� ��������� ������ */

            for(int j=0; j<current.length; j+=2){

                if(next[j] == UNDETERMINED)
                    next[j] = ++numSet;

                if(j != current.length-1)
                    next[j+1] = SET_WALL;
            }


            /* ������� ������� ������ � ���� */
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

        /* ��������� ������ */
        for(int i=1; i<current.length-1; i+=2){ //�������� ������ ��� �����

            /* �������� �� �����������:
             *      ����� �� ����� ����� ����,
             *      �� �������� �� ������ ������ ������
             *�������� ��������� �����, ��� �����������
             */
            if(current[i] == SET_WALL && current[i-1] != current[i+1]
                    && rand.nextBoolean()){


                current[i] = 0; //������ �����

                int old  = Math.max(current[i-1],current[i+1]);
                fNext= Math.min(current[i-1],current[i+1]);

                // ���������� ��� ������ � ����
                  
                 
                for(int j=0; j<current.length; j++){

                    if(current[j] == old)
                        current[j] = fNext;
                }
                    }
        }
    }


    /* �������� ������� ������������ ���� ��� ������� ������, ����������, 
     * ��� ���� �� ������� ���� 1 ������������ ���� � ������
     */
    private void makeVerticalCuts()
    {
        Random   rand          = new Random();

        int      begining;     //������ ������(������������)
        int      end;          //����� �����(������������)

        boolean madeVertical;  //�������� ������������� �������
                                

        int i;
        begining = 0;
        do{

            /* ����� ����� */
            i=begining;
            while(i<current.length-1 && current[i] == current[i+2]){
                i+=2;
            }
            end = i;

            //������� ����� 
              
             
            madeVertical = false;
            do{
                for(int j=begining; j<=end; j+=2){

                    if(rand.nextBoolean()){
                        next[j] = current[j];
                        madeVertical = true;
                    }
                }
            }while(!madeVertical);

            begining = end+2;  //������� � ���������� ������ � ������

        }while(end != current.length-1);
    }




    private void makeLastRow()
    {

        /* ��������� ������� ������ */
        for(int i=0; i<current.length; i++){
            current[i] = next[i];
        }

        for(int i=1; i<current.length-1; i+=2){

            if(current[i] == SET_WALL && current[i-1] != current[i+1]){

                current[i] = 0;

                int old  = Math.max(current[i-1],current[i+1]);
                fNext2= Math.min(current[i-1],current[i+1]);

                // ���������� ��� ������ � ����
                for(int j=0; j<current.length; j++){

                    if(current[j] == old)
                        current[j] = fNext2;
                }
            }
        }


        /* ���������� ��������� ������ */
        for(int k=0; k<current.length; k++){

            if(current[k] == SET_WALL){
                feild[rows-2][k+1] = new Wall(rows-2,k+1,img);
            }else{
                feild[rows-2][k+1] = new Path(rows-2,k+1,img1);
            }
        }

    }

    public void makeOpenings(){

        Random rand = new Random(); //��� ��������� ����������� ��������� �����
        Random rand2 = new Random();//�� ������ ������

        //��������� ����� ��� ����� � ������
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
