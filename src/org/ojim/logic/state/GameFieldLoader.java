package org.ojim.logic.state;

import java.util.HashMap;
import java.util.Map;

import org.jdom.DataConversionException;
import org.jdom.Element;
import org.ojim.log.OJIMLogger;
import org.ojim.logic.ServerLogic;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.FieldGroup;
import org.ojim.logic.state.fields.Street;
import org.ojim.logic.state.fields.TaxField;

public class GameFieldLoader {
	
	public enum FieldName {
//		STREET,
//		CARD,
//		GO,
//		TAX,
//		STATION,
//		JAIL,
//		INFRASTRUCTURE,
//		FREE_PARKING,
//		GO_TO_JAIL;
		STREET("street"),
		CARD("card"),
		GO("go"),
		TAX("tax"),
		STATION("station"),
		JAIL("jail"),
		INFRASTRUCTURE("infrastructure"),
		FREE_PARKING("freeparking"),
		GO_TO_JAIL("gotojail");
		
		public final String name;
		
		private static final Map<String, FieldName> FIELD_NAME = new HashMap<String, FieldName>(FieldName.values().length);
		
		public static FieldName getNameToString(String name) {
			return FIELD_NAME.get(name);
		}
		
		private FieldName(String name) {
			this.name = name;
			if (placeName(name)) {
				throw new IllegalArgumentException("Name is already set! (" + name + ")");
			}
		}
		
		private boolean placeName(String name) {
			return FIELD_NAME.put(name, this) != null;
		}
	}
	
	public static Field readElement(Element element, ServerLogic logic, Map<Integer, FieldGroup> groups) throws DataConversionException {
		FieldName fieldName = FieldName.getNameToString(element.getName());
		switch (fieldName) {
		case STREET :
			return new Street(element, logic, groups);
//		case CARD :
//			break;
//		case GO :
//			break;
		case TAX :
			return new TaxField(element, groups, logic);
//		case STATION :
//			return new Station
//			break;
//		case JAIL :
//			return new Jai
//			break;
//		case INFRASTRUCTURE :
//			return new Infr
//			break;
//		case FREE_PARKING :
//			return new FreeP
//			break;
//		case GO_TO_JAIL :
//			return new GoToJ
//			break;
		default :
			OJIMLogger.getLogger(GameFieldLoader.class.toString()).warning("Requested unrecognized field (" + (fieldName == null ? "name: " + element.getName() : fieldName.toString() + " name: " + fieldName.name));
			return null;
		}
	}
	
}
