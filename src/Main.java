import Shapes.Circle;
import Shapes.Rectangle;
import Shapes.Shape;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(15.0);
        Rectangle rectangle = new Rectangle(10, 10);

        logResults("circle.log", "Circle", circle);
        logResults("rectangle.log", "Rectangle", rectangle);
        logXMLResults("circle.xml", "Circle", circle);
        logXMLResults("rectangle.xml", "Rectangle", rectangle);
    }

    private static void logResults(String fileName, String shapeType, Shape shape) {
        File logFile = new File(fileName);

        try (FileWriter fileWriter = new FileWriter(logFile);) {
            fileWriter.write(shapeType + " Results:\n");
            fileWriter.write("Area: " + shape.getArea() + "\n");
            fileWriter.write("Perimeter: " + shape.getPerimeter() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void logXMLResults(String fileName, String shapeType, Shape shape) {
        File logFile = new File(fileName);

        try (FileWriter fileWriter = new FileWriter(logFile);) {
            fileWriter.write("<" + shapeType + ">\n");
            fileWriter.write("  <Area>" + shape.getArea() + "</Area>\n");
            fileWriter.write("  <Perimeter>" + shape.getPerimeter() + "</Perimeter>\n");
            fileWriter.write("</" + shapeType + ">\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}