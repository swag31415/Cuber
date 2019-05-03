package Cuber.Cube;

public enum Moves {

    r(CubeMappings.m, 2, 4, false), 
    m(CubeMappings.m, 1, -1, false), 
    l(CubeMappings.m, 0, 3, true),

    d(CubeMappings.e, 2, 2, false), 
    e(CubeMappings.e, 1, -1, false), 
    u(CubeMappings.e, 0, 1, true),

    f(CubeMappings.s, 2, 0, false), 
    s(CubeMappings.s, 1, -1, false), 
    b(CubeMappings.s, 0, 5, true);

    private CubeMappings mapType;
    private int pos;
    private int impl;
    private boolean isReversed;
    private Color[][] move;
    private Color[][] implMove;

    private Moves(CubeMappings mapType, int pos, int impl, boolean isReversed) {
        this.mapType = mapType;
        this.pos = pos;
        this.isReversed = isReversed;
        this.impl = impl;
    }

    public CubeMappings getMapType() {
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