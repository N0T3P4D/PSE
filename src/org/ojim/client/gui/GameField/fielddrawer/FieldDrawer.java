/*  Copyright (C) 2010 - 2011  Fabian Neundorf, Philip Caroli,
 *  Maximilian Madlung,	Usman Ghani Ahmed, Jeremias Mechler
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.ojim.client.gui.GameField.fielddrawer;

import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.GoField;
import org.ojim.logic.state.fields.Jail;

/**
 * A abstract drawer to draw a field. For every field class at least one drawer
 * has to exists.
 * 
 * Any new drawer has to be set in the internal drawermap. Either by register
 * himself or by adding this class in the static block.
 * 
 * Because this is an abstract class it is not possible to create an instance of
 * this class. Therefore a {@link #getDrawer(Field)} exists, which returns a
 * drawer for a given field-class.
 * 
 * @author Fabian Neundorf.
 */
public abstract class FieldDrawer {

	/** Internal map to store which drawer belongs to which fieldclass. */
	private static final Map<Class<? extends Field>, Class<? extends FieldDrawer>> drawerMap = new HashMap<Class<? extends Field>, Class<? extends FieldDrawer>>();

	/*
	 * Add here all drawers to the internal map! Or call somewhere in the
	 * subclass the static method register. This has to be done BEFORE the first
	 * need of the class.
	 */
	static {
		drawerMap.put(GoField.class, GoFieldDrawer.class);
		drawerMap.put(Jail.class, JailDrawer.class);
	}

	protected Field field;

	public FieldDrawer(Field field) {
		this.setField(field);
	}

	/**
	 * This constructor only exists to create via {@link #getDrawer(Field)}. Use
	 * {@link #FieldDrawer(Field)} instead!
	 */
	//TODO: (xZise) Kann .newInstance() auch geschützte Konstruktoren aufrufen?
	protected FieldDrawer() {
	}

	private void setField(Field field) {
		this.field = field;
	}

	public abstract void drawTo(Graphics g);

	protected static void register(Class<? extends Field> fieldClass,
			Class<? extends FieldDrawer> drawerClass) {
		drawerMap.put(fieldClass, drawerClass);
	}

	/**
	 * Returns the drawer to the given field and sets the field property of this
	 * new drawer to the given field.
	 * 
	 * @param field
	 *            The field.
	 * @return The new drawer.
	 */
	public static FieldDrawer getDrawer(Field field) {
		Class<? extends FieldDrawer> drawer = drawerMap.get(field.getClass());
		if (drawer == null) {
			throw new IllegalArgumentException("The given field has no drawer.");
		}
		FieldDrawer newDrawer = null;
		try {
			newDrawer = drawer.getConstructor(Field.class).newInstance(field);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDrawer;
	}
}
