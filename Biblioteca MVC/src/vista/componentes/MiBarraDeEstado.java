package vista.componentes;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
/**
 * Esta clase permite crear un panel con la intención de poder mantener
 * informado al usuario con los distintos mensajes de la aplicación
 * @author AGE
 * @version 2
 */
public class MiBarraDeEstado extends JPanel {
    private static MiBarraDeEstado miBarraDeEstado=null;
    private JLabel info=new JLabel();

    private MiBarraDeEstado(){
        Border border=BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        setBorder(border);
        setLayout(new FlowLayout());
        ((FlowLayout) getLayout()).setAlignment(FlowLayout.LEFT);
        info.setForeground(Color.GRAY);
        info.setFont(new Font("verdana",Font.ITALIC,12));
        add(info);
    }

    /**
     * método para cambiar el mensaje a mostrar
     * @param texto contiene dicho mensaje
     */
    public void setInfo(String texto){
        info.setText(texto);
    }

    /**
     * Este metodo instancia un objeto global que contiene
     * la barra de estado común para toda la aplicación
     * @return dicho objeto global
     */
    public static MiBarraDeEstado getInstance(){
        if (miBarraDeEstado==null) {
            miBarraDeEstado=new MiBarraDeEstado();
        }
        return miBarraDeEstado;
    }

}