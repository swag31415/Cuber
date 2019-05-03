package Cuber.Cube;

public class Color {

    public enum Colors {
        White('W'), Blue('B'), Green('G'), Orange('O'), Red('R'), Yellow('Y');

        private char sym;

        private Colors(char sym) {
            this.sym = sym;
        }

        @Override
        public String toString() {
            return sym + "";
        }
    }

    private Colors colorVal;

    public Color(Colors colorVal) {
        this.colorVal = colorVal;
    }

    public Colors getColor() {
        return this.colorVal;
    }

    public void setColor(Colors colorVal) {
        this.colorVal = colorVal;
    }

    @Override
    public String toString() {
        return getColor().toString();
    }
}