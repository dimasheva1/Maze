
import java.util.*;
public class Solver {
	private Cell[][] maze;  //��������� ������ ��� ������������� ���������

	private ArrayDeque<Integer[]> path;  //���� ������������ ��� ������������ ��������������

	private int rows;  //������
	private int cols;  //�������
    
	private int kol=0;


    public Solver(Cell[][] feild)
    {
        //�������������� ������ ����������� ��������� � ����� ���������� �������
        maze = new Cell[feild.length][feild[1].length];

        //�������� ���������� �������� ������� � ������ ����������� ���������
        for(int i=0; i<feild.length; i++){
            for(int j=0; j<feild[i].length; j++){
                maze[i][j] = feild[i][j];
            }
        }

        //��������� ����� � ��������
        rows = feild.length;
        cols = feild[0].length;

        //�������������� ����, ������� ����������� ���� � ��������� � ������� �������
        path = new ArrayDeque<Integer[]>(rows*cols);

    }//����������� ����� )



    //���������� ��������

    public Cell[][] getMaze()
    {
        return maze;
    }

    public void setInitialLocation()
    {

        Integer[] temp = new Integer[2]; //������


        //������� � ����� �����
        for(int i=0; i<maze.length; i++)
        {
            //���� �� ������� ������ �����, ��� ����
            if(maze[i][0].getFlag()==' '){

                //����� �������� ���������
                temp[0] = i;
                temp[1] = 0;

                //������������� ��������� -
                maze[temp[0]][temp[1]].setFlag('-');
                path.addFirst(temp);

            }
        }
    }


    public int canSolveUp()
    {
        Integer[] temp = new Integer[2]; //������ ��� ��������� �������
        temp = path.peekFirst();

        int nRow = temp[0]-1; //������������� ��������� ��������������
        int nCol = temp[1];

        //���� ����� ���� ���������� ����, ������� 1
        if(nRow > 0){
            if( maze[nRow][nCol].getFlag() == ' '){
                return 1;
            }
        }

        return 0; //����� 0

    }

    public int canSolveRight()
    {
        Integer[] temp = new Integer[2]; //�������� ������
        temp = path.peek();

        int nRow = temp[0];   //��������� ������
        int nCol = temp[1]+1;

        //���� ����� ���� ���������� ����, ������� 2
        if(nCol < cols){
            if( maze[nRow][nCol].getFlag() == ' '){
                return 2;
            }
        }

        return 0; //����� 0

    }

    public int canSolveDown()
    {
        Integer[] temp = new Integer[2]; //�������� ������
        temp = path.peek();

        int nRow = temp[0]+1;  //������� ������
        int nCol = temp[1];

        //���� ����� ���� ���������� ����, ������� 3
        if(nRow < rows){
            if( maze[nRow][nCol].getFlag() == ' '){
                return 3;
            }
        }

        return 0;  //����� 0

    }

    public int canSolveLeft()
    {
        Integer[] temp = new Integer[2]; //��������� ������
        temp = path.peek();

        int nRow = temp[0];   //��������� ������
        int nCol = temp[1]-1;


        //���� ����� ���� ���������� ����, ������� 4
        if(nCol > 0){
            if( maze[nRow][nCol].getFlag() == ' '){
                return 4;
            }
        }

        return 0; //����� 0

    }



    public int[] canSolve()
    {
        int[] cut = new int[4];
        int place =0;
        //�������� �����������(����)
        if(canSolveUp() !=   0){
            cut[place] = canSolveUp();
            place++;
        }
        //�������� �����������(������)
        if(canSolveRight() != 0){
            cut[place] = canSolveRight();
            place++;
        }
        //�������� �����������(����)
        if(canSolveDown() != 0){
            cut[place] = canSolveDown();
            place++;
        }
        //�������� �����������(�����)
        if(canSolveLeft() != 0){
            cut[place] = canSolveLeft();
            place++;
        }

        //���������� ����, ���� ��� �����������
        if(place == 0){
            for(int i = 0; i<4; i++){
                cut[i] = 0;
            }
            return cut;

        } else { //����� ��������� ������ � ������� ���
            int[] cancut = new int[place];
            for(int i=0; i<place; i++){
                cancut[i] = cut[i];
            }

            return cancut;
        }
    }



    public void solveUp()
    {
        Integer[] current = path.peek(); //������� ���������
        Integer[] temp = new Integer[2]; //��������� ���������� ���������

        int nRow = current[0]-1;  //������� ������
        int nCol = current[1];


        maze[nRow][nCol].setFlag('-'); //������������� ���������

        temp[0] = nRow;
        temp[1] = nCol;

        path.addFirst(temp);  //�������� � ����, ������������� ��������� -

    }

    public void solveRight()
    {
        Integer[] current = path.peek(); //�������� ���������
        Integer[] temp = new Integer[2]; //��������� ���������

        int nRow = current[0];   //������� ������
        int nCol = current[1]+1;
        maze[nRow][nCol].setFlag('-');  //������������� ���������

        temp[0] = nRow;
        temp[1] = nCol;

        path.addFirst(temp); //�������� � ����, ������������� ��������� -

    }

    public void solveDown()
    {
        Integer[] temp = new Integer[2]; //������� ���������
        Integer[] current = path.peek(); //���������� ���������� ���������

        int nRow = current[0]+1;   //�������
        int nCol = current[1];
        maze[nRow][nCol].setFlag('-');  //�������� � ����, ������������� ��������� -

        temp[0] = nRow;
        temp[1] = nCol;

        path.addFirst(temp); //pushes the next location to the stack

    }

    public void solveLeft()
    {
        Integer[] temp = new Integer[2]; //������� ���������
        Integer[] current = path.peek(); //��������� ���������� ���������

        int nRow = current[0];   //�������
        int nCol = current[1]-1;
        maze[nRow][nCol].setFlag('-');  //�������� � ����, ������������� ��������� -

        temp[0] = nRow;
        temp[1] = nCol;

        path.addFirst(temp); //��������� � ����

    }

    public boolean nearExit()
    {
        Integer[] temp = new Integer[2];
        temp = path.peek();

        if(temp[1] == maze[0].length-2){
            if(maze[temp[0]][temp[1]+1].getFlag() == ' '){
                return true;
            }
        }

        return false;
    }

    public void goToExit()
    {
        Integer[] temp = new Integer[2]; //������ � �����
        Integer[] current = path.peek(); //������� ���������

        temp[0] = current[0];   //����� ������
        temp[1] = current[1]+1;

        maze[temp[0]][temp[1]].setFlag('-'); //������������� ���������  -

        path.push(temp); //��������� � ����

    }

    public void backTrack()
    {
        path.removeFirst();

    }


    public void solvePath()
    {
        Integer[] temp = new Integer[2]; //������ � �����


        //���� ������� � �����
        while(path.peek() != null){

            temp = path.pop(); //������� ���������

            maze[temp[0]][temp[1]].setFlag('+'); //������������� ��������� +
            kol++;
        }

        //cleans up the maze
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(maze[i][j].getFlag() =='-')
                    maze[i][j].setFlag(' ');;
            }
        }

    }

    public void solveMaze()
    {


        int[] solve_order; //������ ��������� ���� ��� ������� ���������

        setInitialLocation(); //����� ������ ���������

        while( ! nearExit() ){

            solve_order = canSolve(); /*��������� ��������� �����������*/

            if(solve_order[0] == 0){  //���� �����
                backTrack();    //���� ����� � ������ ������ �����������

            }else{ //������� ����������� ��� �������
                if(solve_order[0] == 1){
                    solveUp();
                }else if(solve_order[0] == 2){
                    solveRight();
                }else if(solve_order[0] == 3){
                    solveDown();
                }else if(solve_order[0] == 4){
                    solveLeft();
                }
            }

        }

        goToExit(); //����� ������ �� ����� ���� �����
        //���� ��������� - ����� ���� � ����

        solvePath(); //���� ����
        //������������� ��������� +


    }

    public Cell [][] getSolution()
    {
        return maze;
    }

    /*public void printSolution()
    {
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                System.out.print(maze[i][j].getFlag());
            }
            System.out.println();
        }
    }*/
    
    public int getKolCell()
    {
    	return kol;
    }
   /* public static void main(String [] args)
    {
    	MapMaker a = new MapMaker(10,10);
    	a.makeMaze();
    	Solver b = new Solver(a.getMaze());
    	b.solveMaze();
    	b.printSolution();
    	System.out.println(b.getKolCell());
    	
    }*/
}
