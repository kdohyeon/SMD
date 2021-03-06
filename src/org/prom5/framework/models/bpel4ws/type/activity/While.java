/*
 * Created on 15-02-2005
 *
 */
package org.prom5.framework.models.bpel4ws.type.activity;

import org.prom5.framework.models.bpel4ws.type.BPEL4WS;
import org.prom5.framework.models.bpel4ws.type.BPEL4WSVisitor;

/**
 * @author Kristian Bisgaard Lassen
 */
public class While extends Composed {

    /** */
    public final Activity activity;

    /***/
    public final String condition;

    /**
     * @param string
     * @param activity
     * @param condition
     */
    public While (String string, Activity activity, String condition) {
        super (string);
        this.activity = activity;
        this.condition = condition;
    }

    /**
     * @see type.bpel4ws.BPEL4WSVisitable#acceptVisitor(type.bpel4ws.BPEL4WSVisitor)
     */
    public void acceptVisitor (BPEL4WSVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * @see type.bpel4ws.activity.Activity#cloneActivity()
     */
    @Override
    public Activity cloneActivity () {
        return new While(name,activity.cloneActivity(),condition);
    }

	public String writeToDot(BPEL4WS model) {
		String s = "    n" + activity + " [label=\"" + name + "];\n";
		return s;
	}
}
