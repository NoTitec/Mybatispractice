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
        MyBoardDAO myBoardDAO = new MyBoardDAO(MybatisConnectionFactory.getSqlSessionFactory());
        BoardView boardView = new BoardView();
        MyBoardService MyBoardService = new MyBoardService(myBoardDAO);
        //유저가 board의 모든정보 요청
        List<BoardDTO> all = MyBoardService.selectall();
        boardView.printAll(all);

        while (true) {
            System.out.println("*-----------------------------------------------------*");
            System.out.println("menu");
            System.out.println("1.insert 2. update 3.delete 4.exit");
            System.out.println("*-----------------------------------------------------*");
            int mn = sc.nextInt();
            if(mn==4){
                System.out.println("program exit");
                break;
            }
            switch (mn) {
                case 1:  //유저가  insert 요청
                String msg=MyBoardService.insertnewboard();
                System.out.println(msg);
                break;
                case 2://유저가 특정 board_id정보를 수정
                long snum=9l;
                String umsg=MyBoardService.updateselectrow(snum);
                System.out.println(umsg);
                break;
                case 3://유저가 특정 board_id정보를 삭제
                long dnum = 9l;
                String dmsg = MyBoardService.deleteselectrow(dnum);
                System.out.println(dmsg);
                break;
            }
        }
    }
}
