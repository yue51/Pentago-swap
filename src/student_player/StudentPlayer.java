package student_player;
import student_player.MyTools;
import boardgame.Move;
import boardgame.Player;
import pentago_swap.PentagoPlayer;
import pentago_swap.PentagoBoardState;
import pentago_swap.PentagoMove;

import java.util.ArrayList;

/** A player file submitted by a student. */
public class StudentPlayer extends PentagoPlayer {

    /**
     * You must modify this constructor to return your student number. This is
     * important, because this is what the code that runs the competition uses to
     * associate you with your agent. The constructor should do nothing else.
     */
    public StudentPlayer() {
            super("260740996");
        }

        /**
         * This is the primary method that you need to implement. The ``boardState``
         * object contains the current state of the game, which your agent must use to
         * make decisions.
        */
public Move chooseMove(PentagoBoardState boardState) {

        //Double myMove = MyTools.minmax(boardState,depth);
         //System.out.println("AI is moving");

         int player_id = boardState.getTurnPlayer();
          PentagoBoardState.Piece turnPiece;

       if(player_id< 1){ turnPiece= PentagoBoardState.Piece.WHITE;}
       else {turnPiece = PentagoBoardState.Piece.BLACK;}



//          /long startTime = System.currentTimeMillis();
       
         PentagoMove bestMove = MyTools.Wrapper(boardState,player_id,2);

        // Move myMove1 = boardState.getRandomMove();
         //long endTime = System.currentTimeMillis();
        // System.out.println("The time is : "+(endTime-startTime));


        //Return your move to be processed by the server.
        return bestMove;
        }
}