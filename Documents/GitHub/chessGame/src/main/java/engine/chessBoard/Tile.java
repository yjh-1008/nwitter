package engine.chessBoard;

import engine.pieces.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Tile {
    protected final int tileCoordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TTILES_CACHE = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for(int i=0;i<BoardUtils.NUM_TILES;i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }


        return Collections.unmodifiableMap(emptyTileMap);
    }

    public int getTileCoordinate(){
        return this.tileCoordinate;
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece !=null ?  new OccupiedTile(tileCoordinate,piece):EMPTY_TTILES_CACHE.get(tileCoordinate);
    }

    private Tile(int tileCoordinate){
        this.tileCoordinate=tileCoordinate;
    }

    public abstract boolean isTileOccupied();//isEmpty?

    public abstract Piece getPiece();
    //isEmpty space?
    public static final class EmptyTile extends Tile{
        EmptyTile(final int cordinate){
            super(cordinate);
        }

        @Override
        public String toString(){
            return "-";
        }

        @Override
        public boolean isTileOccupied(){
            return false;
        }

        @Override
        public Piece getPiece(){
            return null;
        }
    }
    //isEmpty space
    public static final class OccupiedTile extends Tile{
        private final Piece pieceOnTile;
        public OccupiedTile(int tileCoordinate, Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile= pieceOnTile;
        }

        @Override
        public String toString(){
            return getPiece().getPieceAlliance().isBlack()? getPiece().toString().toLowerCase():
                    getPiece().toString();
        }

        @Override
        public boolean isTileOccupied(){
            return true;
        }
        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }
    }
}
