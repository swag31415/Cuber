package Cuber.Cube;

public enum Moves {

    r(Mappings.m, 2, 4, false), 
    m(Mappings.m, 1, -1, false), 
    l(Mappings.m, 0, 3, true),

    d(Mappings.e, 2, 2, false), 
    e(Mappings.e, 1, -1, false), 
    u(Mappings.e, 0, 1, true),

    f(Mappings.s, 2, 0, false), 
    s(Mappings.s, 1, -1, false), 
    b(Mappings.s, 0, 5, true);

    private Mappings mapType;
    private int pos;
    private int impl;
    private boolean isReversed;
    private Color[][] move;
    private Color[][] implMove;

    private Moves(Mappings mapType, int pos, int impl, boolean isReversed) {
        this.mapType = mapType;
        this.pos = pos;
        this.isReversed = isReversed;
        this.impl = impl;
    }

    public Mappings getMapType() {
        return this.mapType;
    }

    public int getPos() {
        return this.pos;
    }

    public boolean getIsReversed() {
        return this.isReversed;
    }

    public void setMove(Color[][] move) {
        this.move = move;
    }

    public Color[][] getMove() {
        return this.move;
    }

    public void setImplMove(Color[][] implMove) {
        this.implMove = implMove;
    }

    public Color[][] getImplMove() {
        return this.implMove;
    }

    public int getImplicated() {
        return this.impl;
    }
}