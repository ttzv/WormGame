package wormgame.gui;

import wormgame.Direction;
import wormgame.domain.Worm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingAction extends AbstractAction implements ActionListener {

	private JComponent component;
	private Worm worm;

	private Direction direction;


	public MovingAction(JComponent component, Worm worm) {
		this.component = component;
		this.worm = worm;
	}

	public MovingAction(String name, Direction direction, JComponent component, Worm worm) {
		super(name);
		this.component = component;
		this.worm = worm;
		this.direction = direction;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		this.worm.setDirection(this.direction);
		System.out.println("INPUT DETECTED");

	}
	
	public MovingAction addAction (String nameh, Direction direction) {
		
		MovingAction action = new MovingAction(nameh, direction, this.component, this.worm);
		
		KeyStroke pressedKey = KeyStroke.getKeyStroke(nameh);
		
		InputMap iMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		iMap.put(pressedKey, nameh);
		component.getActionMap().put(nameh, action);
		
		return action;
	
	}

}
