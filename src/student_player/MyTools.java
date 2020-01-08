package student_player;


import pentago_swap.PentagoBoardState;
import pentago_swap.PentagoMove;
import pentago_swap.PentagoBoardState.Piece;

import java.util.ArrayList;


public class MyTools {

    public static int maxPlayer, minPlayer ;
    public static Piece turnPiece;

    public static PentagoMove Wrapper(PentagoBoardState board, int playerId,int depth) throws IllegalArgumentException {

        maxPlayer = playerId;
        minPlayer = 1- playerId;
        if(maxPlayer < 1){ turnPiece=Piece.WHITE;}
        else {turnPiece = Piece.BLACK;}

        if (game_over(board)){
           // System.out.println("From game over");
            IllegalArgumentException gameOver = new IllegalArgumentException();
            throw  gameOver;
        }
        ArrayList<PentagoMove> moves = board.getAllLegalMoves();
        int bestScore = Integer.MIN_VALUE;
        PentagoMove Bestmove = null;
        for(PentagoMove move: moves){
            PentagoBoardState newBoard = (PentagoBoardState) board.clone();
            newBoard.processMove(move);
            int score = minmaxAlphaBeta(newBoard ,depth-1,false,Integer.MIN_VALUE,Integer.MAX_VALUE);
            //System.out.println("Call after minmax call:  "+score);
            if(score > bestScore){
                bestScore = score;
                Bestmove = move;
               // System.out.println("Best move updated");
            }
        }
        return Bestmove;
    }
    public static int minmaxAlphaBeta(PentagoBoardState board,int depth,boolean maxmizing,int alpha,int beta){

        if(depth == 0 || game_over(board)) {
            //System.out.println("From game over, the depth is: " + depth);
            return evalUtility(board,turnPiece);
        }
        ArrayList<PentagoMove> moves = board.getAllLegalMoves();

        // when in maximizer
        if (maxmizing){
            for(PentagoMove move: moves){
                PentagoBoardState newBoard = (PentagoBoardState) board.clone();
                newBoard.processMove(move);
                int temp_val =Math.max(alpha,minmaxAlphaBeta(newBoard,depth-1,false,alpha,beta));
                if(alpha < temp_val) alpha = temp_val;break;

            }
            return alpha;
        }
        // when in minimizer
        else {
            for(PentagoMove move: moves){
                PentagoBoardState newBoard = (PentagoBoardState) board.clone();
                newBoard.processMove(move);
                int temp_val = Math.min(beta,minmaxAlphaBeta(newBoard,depth-1,true,alpha,beta));
                if(beta > temp_val) beta = temp_val;break;
            }
            return beta;
        }
    }
    public static boolean game_over(PentagoBoardState boardState){
        ArrayList<PentagoMove> moves = boardState.getAllLegalMoves();
        if(moves.isEmpty()){
            return true;
        }
        return false;
    }
    // evaluate the current board
    // calculating the utility
    public static int evalUtility(PentagoBoardState board,Piece color) {

        int countPiece = 0;
        int countAnother = 0;

     for(int i = 0; i < 6; i++) {
         for (int j = 0; j < 5; j++) {
             if (board.getPieceAt(i, j).equals(color) && board.getPieceAt(i, j + 1).equals(color)) {
                 countPiece += countAnother;
                 countAnother ++;
             }
             else {
                 countAnother=0;
             }
         }
     }
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (board.getPieceAt(i, j).equals(color) && board.getPieceAt(i+1, j).equals(color)) {
                    countPiece += countAnother;
                    countAnother ++;
                }
                else {
                    countAnother=0;
                }
            }
        }
        for(int i = 0; i < 5; i++) {
            if (board.getPieceAt(i, i).equals(color) && board.getPieceAt(i+1, i+1).equals(color)) {
                countPiece += countAnother;
                countAnother ++;
            }
            else {
               countAnother = 0;
            }

        }
        for(int i = 0; i < 5; i++) {
            if (board.getPieceAt(i, 5-i).equals(color) && board.getPieceAt(i+1, 4-i).equals(color)) {
                countPiece += countAnother;
                countAnother ++;
            }
            else {
               countAnother = 0;
            }
        }
        return countPiece;
    }

}

