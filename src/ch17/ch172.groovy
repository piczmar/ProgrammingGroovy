package ch17

import groovy.swing.SwingBuilder
import java.awt.FlowLayout
import javax.swing.WindowConstants

/**
 * Building Swing
 */
bldr = new SwingBuilder()
frame = bldr.frame(
        title: "Swing",
        size: [50, 100],
        layout: new FlowLayout(),
        defaultCloseOperation: WindowConstants.EXIT_ON_CLOSE
) {
    lbl = label(text: 'test')
    btn = button(text: 'Click me', actionPerformed: {
        btn.text = 'Clicked'
        lbl.text = "Groovy!"
    })
}
frame.show()
