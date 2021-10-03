/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di_t1a1_ap42_ej4colourful;


import static java.lang.Math.random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author usuario
 */
public class DI_T1A1_Ap42_Ej4Colourful extends Application {
    @Override
    public void start(Stage primaryStage) {
        //En este caso vamos a hacer un Group ya que se adapta al tamño de todos los nodos en su interior.
        Group root = new Group();
        Scene scene = new Scene (root, 800,600, Color.BLACK);
        //Damos la escena a la Ventana
        primaryStage.setScene(scene);
        
        //Definimos el grupo de circulos
        Group circles = new Group();
        for(int i = 0; i < 30; i++)
        {
            //Definimos 30 circulos de radio 150, color blanco con opacidad 5%
            Circle circle = new Circle(150, Color.web("white", 0.05));
            //Establecemos el tipo de trazo, al ser OUTSIDE quiere decir que empieza desde fuera
            circle.setStrokeType(StrokeType.OUTSIDE);
            //Escogemos el color del tipo de trazo
            circle.setStroke(Color.web("white", 0.16));
            //Escogemos la amplitud del tipo de trazo
            circle.setStrokeWidth(4);
            //Añadimos el circulo al grupo circles creado anteriormente
            circles.getChildren().add(circle);
        }
        
        //Introducimos  aqui el rectangulo de color para que esté por detrás de los circulos
        //Declaramos sus medidas que sean como el ancho y alto de la escena y tiene un gradiente linear
        //El gradiente linear comienza en la parte inferior izquierda y termina en la parte superior derecha
        //El valor de true en el gradiente significa que el gradientes proporcional y NO_CYCLE indica que el color no es cíclico
        //El stop indica que el gradiente de colores debe tener un lugar específico
        Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(), 
                new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new 
                Stop[]{
                    new Stop(0, Color.web("#f8bd55")),
                    new Stop(0.14, Color.web("#c0fe56")),
                    new Stop(0.28, Color.web("#5dfbc1")),
                    new Stop(0.43, Color.web("#64c2f8")),
                    new Stop(0.57, Color.web("#be4af7")),
                    new Stop(0.71, Color.web("#ed5fc2")),
                    new Stop(0.85, Color.web("#ef504c")),
                    new Stop(1, Color.web("#f2660f")),}));
        //Estas dos últimas lineas sirven para que el rectángulo se adapte si se cambian las propiedades del tamaño de la escena
        //Tanto en ancho(width) com oen alto(height)
        colors.widthProperty().bind(scene.widthProperty());
        colors.heightProperty().bind(scene.heightProperty());
        
        //El grupo siguiente, configura la estructura de mezlca de la capa
        //El grupo cotiene dos hijos, 1. Un grupo que contiene un rectángulo negro y el grupo de circulos.
        //                            2. El rectángulo denominado colors.
        Group blendModeGroup = new Group(new Group(new Rectangle(scene.getWidth(), scene.getHeight(),
                    Color.BLACK), circles), colors); 
        
        //Aplica la mezcla de la capa del rectangulo colors. 
        colors.setBlendMode(BlendMode.OVERLAY);
        
        //Añade blendModeGroup al root node
        root.getChildren().add(blendModeGroup);
 
        //Damos efecto de desenfoque a los circulos
        circles.setEffect(new BoxBlur(10,10,3));
        
        //Hace una linea temporal desde 0 hasta 40
        Timeline timeline = new Timeline();
        //El loop añade los dos key frames para cada uno de los 30 circulos
        for(Node circle: circles.getChildren()) {
            //Escoge una posición random dentro de la ventana al principio.
            timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, //set start  position at 0
                new KeyValue(circle.translateXProperty(), random() * 800),
                new KeyValue(circle.translateYProperty(), random() * 600)
            ),
            //Este segundo KeyFrame hace lo mismo que el primero pero tras pasar 40 segundos
            new KeyFrame(new Duration(40000), //set end position at 40s
                    new KeyValue(circle.translateXProperty(), random() * 800),
                    new KeyValue(circle.translateYProperty(), random() * 600)
                )
            );
        }
        //Comienza la animación
        //play 40s of animation
        timeline.play();
        
        //Mostramos la ventana
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    
}
