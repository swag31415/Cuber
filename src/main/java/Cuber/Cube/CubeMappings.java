package Cuber.Cube;

public enum CubeMappings {

    m(0, 1, 5, 2, FaceType.Column, FaceType.Column, FaceType.PosRevEntryRevColumn, FaceType.Column),
    s(1, 4, 2, 3, FaceType.Row, FaceType.PosRevColumn, FaceType.PosRevEntryRevRow, FaceType.EntryRevColumn),
    e(0, 4, 5, 3, FaceType.Row, FaceType.Row, FaceType.Row, FaceType.Row);

    private Face face0, face1, face2, face3;

    private CubeMappings(int face0, int face1, int face2, int face3, FaceType type0, FaceType type1, FaceType type2, FaceType type3) {
        this.face0 = new Face(face0, type0);
        this.face1 = new Face(face1, type1);
        this.face2 = new Face(face2, type2);
        this.face3 = new Face(face3, type3);
    }

    public Face[] getFaces() {
        Face[] faces = {face0, face1, face2, face3};
        return faces;
    }

    private enum FaceType {
        Row(false, false, true),
        Column(false, false, false),
        PosRevRow(true, false, true),
        PosRevColumn(true, false, false),
        EntryRevRow(false, true, true),
        EntryRevColumn(false, true, false),
        PosRevEntryRevRow(true, true, true),
        PosRevEntryRevColumn(true, true, false);

        boolean isPosRev;
        boolean isEntryRev;
        boolean isRow;

        private FaceType(boolean isPosRev, boolean isEntryRev, boolean isRow) {
            this.isPosRev = isPosRev;
            this.isEntryRev = isEntryRev;
            this.isRow = isRow;
        }
    }

    public class Face {

        private int face;
        private FaceType type;
    
        public Face(int face, FaceType type) {
            this.face = face;
            this.type = type;
        }
    
        public int getFace() {
            return this.face;
        }

        public boolean getIsRow() {
            return type.isRow;
        }

        public boolean getIsPosRev() {
            return type.isPosRev;
        }

        public boolean getIsEntryRev() {
            return type.isEntryRev;
        }
        
    }
}