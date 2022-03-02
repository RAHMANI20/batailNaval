package games;


import java.util.ArrayList;

import affichage.ModelListener;

public abstract class AbstractListenableModel {

    private ArrayList<ModelListener> listeners;

    public AbstractListenableModel() {
        this.listeners = new ArrayList<>();
    }

    public void addListener(ModelListener l) {
        listeners.add(l);
        l.modelUpdated(this);
    }

    public void removeListener(ModelListener l) {
        listeners.remove(l);
    }

    protected void fireChange() {
        for (ModelListener l : listeners)
        {
            l.modelUpdated(this);
        }
    }
}
