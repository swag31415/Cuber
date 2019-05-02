package Cuber;

import java.util.ArrayList;
import java.util.List;

import Cuber.Cube.Colors;

public class Mapping {

    private List<Element> elements;

    public Mapping() {
        this.elements = new ArrayList<Element>();
    }

    public void addElement(int face, Colors[] elements) {
        this.elements.add(new Element(face, elements));
    }

    public Element[] getMap() {
        return (Element[]) elements.toArray();
    }

    public class Element {
        private int face;
        private Colors[] elements;

        public Element(int face, Colors[] elements) {
            this.face = face;
            this.elements = elements;
        }

        private int getFace() {
            return this.face;
        }

        private Colors[] getElements() {
            return this.elements;
        }
    }
}