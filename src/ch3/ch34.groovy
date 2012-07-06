package ch3

import javax.swing.*
import java.awt.FlowLayout
import java.awt.event.ActionListener
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import java.awt.event.FocusListener
/**
 * example how to implement interfaces in Groovy
 * Dynamic event handling in Swing application with Groovy
 */

frame = new JFrame(
        size: [300, 300],
        layout: new FlowLayout(),
        defaultCloseOperation: WindowConstants.EXIT_ON_CLOSE
)

button = new JButton("click")
positionLabel = new JLabel("")
msgLabel = new JLabel("")

frame.contentPane.add(button)
frame.contentPane.add(positionLabel)
frame.contentPane.add(msgLabel)

// grails will inject the closure to every method in ActionListener object
button.addActionListener(
        {JOptionPane.showMessageDialog(frame, "You clicked!")} as ActionListener
)

// we can also assign a closure to variable for reuse
displayMouseLocation = {positionLabel.setText("${it.x}, ${it.y}")}
frame.addMouseListener(displayMouseLocation as MouseListener)
frame.addMouseMotionListener(displayMouseLocation as MouseMotionListener)


// or create a map where keys are method names
handleFocus = [
        focusGained : {msgLabel.setText("Good to see you!")},
        focusLost: {msgLabel.setText("Come back soon!")}
]
button.addFocusListener(handleFocus as FocusListener)

// or dynamically set event handlers in code
events = ["WindowListener", "ComponentListener"]
// above list might come from some input
handler = {msgLabel.setText("${it}")}
for(event in events){
    handlerImpl = handler.asType(Class.forName("java.awt.event.${event}"))
    frame."add${event}"(handlerImpl)
}

frame.show()

