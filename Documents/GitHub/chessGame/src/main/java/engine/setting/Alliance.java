package engine.setting;

import engine.player.BlackPlayer;
import engine.player.Player;
import engine.player.WhitePlayer;

public enum Alliance {
    WHITE{
        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
            return whitePlayer;
        }
    },
    BLACK{
        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public int getDirection() {
            return 1;
        }

        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            return blackPlayer;
        }
    };
    public abstract boolean isWhite();
    public abstract boolean isBlack();
    public abstract int getDirection();

    public abstract Player choosePlayer(final WhitePlayer whitePlayer,final BlackPlayer blackPlayer);
}
