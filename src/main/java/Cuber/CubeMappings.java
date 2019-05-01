package Cuber;

public enum CubeMappings {

    m(0, 1, 5, 2, Set.Column, Set.Column, Set.Column, Set.Column, false, false, true, false),
    s(1, 4, 2, 3, Set.Row, Set.Column, Set.Row, Set.Column, false, false, true, true),
    e(0, 4, 5, 3, Set.Row, Set.Row, Set.Row, Set.Row, false, false, false, false);

    private Face face0, face1, face2, face3;

    private CubeMappings(int face0, int face1, int face2, int face3, Set ori0, Set ori1, Set ori2, Set ori3, boolean isRev0, boolean isRev1, boolean isRev2, boolean isRev3) {
        this.face0 = new Face(face0, ori0, isRev0);
        this.face1 = new Face(face1, ori1, isRev1);
        this.face2 = new Face(face2, ori2, isRev2);
        this.face3 = new Face(face3, ori3, isRev3);
    }

    public Face[] getFaces() {
        Face[] faces = {face0, face1, face2, face3};
        return faces;
    }

    public enum Set {
        Row, Column;
    }

    public class Face {

        private int face;
        private Set ori;
        private boolean isRev;
    
        public Face(int face, Set ori, boolean isRev) {
            this.face = face;
            this.ori = ori;
            this.isRev = isRev;
        }
    
        public int getFace() {
            return this.face;
        }
    
        public Set getOri() {
            return this.ori;
        }
    
        public boolean getIsRev() {
            return this.isRev;
        }
    
        public boolean isIsRev() {
            return this.isRev;
        }
        
    }
}