package gr.uoi.cs.view;

import java.awt.Component;

public interface View<T extends Component> {
	T component();
}
