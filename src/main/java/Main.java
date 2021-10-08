import persistence.MybatisConnectionFactory;
import persistence.dao.MyBoardDAO;
import persistence.dto.BoardDTO;
import service.MyBoardService;
import view.BoardView;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyBoardDAO myBoardDAO = new MyBoardDAO(MybatisConnectionFactory.getSqlSessionFactory());//static 메모리에있는 DBCP객체 DAO인자로 넘겨주면서 DAO생성
        BoardView boardView = new BoardView();
        MyBoardService MyBoardService = new MyBoardService(myBoardDAO);//생성된 DAO객체를 서비스 객체에 넘겨주며 서비스 객체 생성
        //유저가 board의 모든정보 요청
        List<BoardDTO> all = MyBoardService.selectall();
        boardView.printAll(all);

        final int exitnum=6;
        while (true) {
            System.out.println("*-----------------------------------------------------*");
            System.out.println("menu");
            System.out.println("1.insert 2. update 3.delete 4. select one with id 5.select inner selday 6.exit");
            System.out.println("*-----------------------------------------------------*");
            int mn = sc.nextInt();
            if(mn==exitnum){
                System.out.println("program exit");
                break;
            }
            switch (mn) {
                case 1:  //유저가  insert 요청
                String msg=MyBoardService.insertnewboard();
                System.out.println(msg);
                break;
                case 2://유저가 특정 board_id정보를 수정
                long snum=4l;
                String umsg=MyBoardService.updateselectrow(snum);
                System.out.println(umsg);
                break;
                case 3://유저가 특정 board_id정보를 삭제
                long dnum = 4l;
                String dmsg = MyBoardService.deleteselectrow(dnum);
                System.out.println(dmsg);
                break;
                case 4://유저가 특정 board_id정보를 검색
                    long onum = 4l;
                    BoardDTO one=MyBoardService.selectonewith_id(onum);
                    boardView.printone(one);
                    break;
                case 5:
                    int day=7;
                    List<BoardDTO> r=MyBoardService.selectrecent_day(day);
                    boardView.printAll(r);
                    break;
            }
        }
    }
}
