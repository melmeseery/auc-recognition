package gui;

import java.util.EventListener;

public interface HandleStroke extends EventListener {

	public abstract void HandleNewStroke(NewStrokeEvent Evt);

}
