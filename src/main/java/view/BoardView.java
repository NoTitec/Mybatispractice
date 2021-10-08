package view;

import persistence.dto.BoardDTO;

import java.util.List;

public class BoardView {
    public void printAll(List<BoardDTO> dtos){
        System.out.println("모든 게시글");

        /*for(BoardDTO dto:dtos){
            System.out.println("dto.toString() = " + dto.toString());
        }*/
        dtos.stream().forEach(v-> System.out.println("v.toString()="+v.toString()));
    }
    public void printone(BoardDTO one){
        System.out.println("선택된 1개의");
            System.out.println("dto.toString() = " + one.toString());
        }
    }

