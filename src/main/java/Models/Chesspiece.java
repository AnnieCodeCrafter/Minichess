package Models;

import java.util.List;

public class Chesspiece {
    private ChessPieceType chessPieceType;
    private Square start;
    private Square end;

    public ChessPieceType getChessPieceType() {
        return chessPieceType;
    }

    public Square getEnd() {
        return end;
    }

    public Square getStart() {
        return start;
    }

    public void setEnd(Square end) {
        this.end = end;
    }

    public void setStart(Square start) {
        this.start = start;
    }

    public Chesspiece(ChessPieceType chessPieceType, Square start) {
        this.chessPieceType = chessPieceType;
        this.start = start;
    }

    public Chesspiece(ChessPieceType chessPieceType) {
        this.chessPieceType = chessPieceType;
    }

}
