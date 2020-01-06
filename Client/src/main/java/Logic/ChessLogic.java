package Logic;

import Models.ChessPieceType;
import Models.Chesspiece;

public class ChessLogic {
    // pieces: king, bishop, knight, rook, pawn
    //allowed moves:
        // king: [1,1; 1,0; 0,1; -1,-1; -1, 0; 0, -1; -1, 1; 1, -1;]
        // bishop: [1,1; -1,-1; 1,-1; -1,1;]
        // knight: [2,1; 2,-1; -2,1; -2,-1]
        // rook:   [0,1; 1,0; -1,0; 0,-1]
        // pawn: [0,1] (only when punting piece) [1,1;1,-1]




    private void Moveset(ChessPieceType pieceType) {
        switch(pieceType) {
            case KING:
                //check if the following values can be added to the coordinates and be valid
        }


    }
}
