
import jess.Deftemplate;
import jess.Fact;
import jess.Funcall;
import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Value;
public class CreateFacts {
	
	public static int rasp;
	public static Rete engine;
	public static Deftemplate d;
	public static Fact f;
	public static int i;
	
	public CreateFacts() throws JessException
	{
		engine=new Rete();
		d=new Deftemplate("intrebare","Intrebarea",engine);
		d.addSlot("stringIntrebare", Funcall.NIL, "STRING");
		d.addSlot("raspuns", Funcall.NIL, "STRING");
		engine.addDeftemplate(d);
		f=new Fact("intrebare",engine);
		f.setSlotValue("stringIntrebare", new Value(3,RU.INTEGER));
		f.setSlotValue("raspuns", new Value(3,RU.INTEGER));
		engine.assertFact(f);
		i=0;
	}
	public static void createFact() throws JessException {
		engine.store("RASPUNS", new Value(rasp,RU.INTEGER));
		
		String rule="(defrule cat_dormiti" + 
				"(declare (salience 95))"+ 
				"(intrebare (stringIntrebare 3) (raspuns 3))" +
				" =>" + 
				"(bind ?r (fetch RASPUNS))"+ 
				" (assert (intrebare (stringIntrebare Dormiti_minim_6_sau_7_ore_pe_noapte?) (raspuns ?r)))" +
				"(store INTREBARE Dormiti_minim_6_sau_7_ore_pe_noapte?)" +
				"(store RASPUNS 2))";
		
		String rule2="(defrule Manifestati_interes" +
				"(declare (salience 96))"+
				"(intrebare (stringIntrebare Dormiti_minim_6_sau_7_ore_pe_noapte?) (raspuns 0))" +
				"=>" +
				"(bind ?r (fetch RASPUNS))"+
				" (assert (intrebare (stringIntrebare Manifestati_interes_pentru_lucrurile_pe_care_in_mod_normal_le_faceati_cu_placere?) (raspuns ?r)))" +
				"(store INTREBARE Manifestati_interes_pentru_lucrurile_pe_care_in_mod_normal_le_faceati_cu_placere?)" +
				"(store RASPUNS 2))";
		
		String rule3="(defrule depresie"+
				"(declare (salience 97))"+
				"(intrebare (stringIntrebare Manifestati_interes_pentru_lucrurile_pe_care_in_mod_normal_le_faceati_cu_placere?) (raspuns 0))" +
				"=>(store INTREBARE Suferiti_de_depresie)" +
				"(store RASPUNS 2))";
		
		String rule4="(defrule relaxare" +
				"(declare (salience 97))"+
				"(intrebare (stringIntrebare Manifestati_interes_pentru_lucrurile_pe_care_in_mod_normal_le_faceati_cu_placere?) (raspuns 1))" +
				"=>" +
				"(bind ?r (fetch RASPUNS))"+
				"(assert (intrebare (stringIntrebare Aveti_probleme_in_a_va_relaxa?) (raspuns ?r)))" +
				"(store INTREBARE Aveti_probleme_in_a_va_relaxa?)" +
				"(store RASPUNS 2))";
		
		String rule5="(defrule stres"+
				"(declare (salience 98))"+
				"(intrebare (stringIntrebare Aveti_probleme_in_a_va_relaxa?) (raspuns 0))" +
				"=>(store INTREBARE Suferiti_de_stres_acut)" +
				"(store RASPUNS 2))";
		
		String rule6="(defrule anxietate"+
				"(declare (salience 98))"+
				"(intrebare (stringIntrebare Aveti_probleme_in_a_va_relaxa?) (raspuns 1))" +
				"=>(store INTREBARE Suferiti_de_anxietate)" +
				"(store RASPUNS 2))";
		
		String rule7="(defrule halucinatii" +
				"(declare (salience 96))"+
				"(intrebare (stringIntrebare Dormiti_minim_6_sau_7_ore_pe_noapte?) (raspuns 1))" +
				"=>" +
				"(bind ?r (fetch RASPUNS))"+
				" (assert (intrebare (stringIntrebare Auziti_sunete_sau_aveti_halucinatii?) (raspuns ?r)))" +
				"(store INTREBARE Auziti_sunete_sau_aveti_halucinatii?)" +
				"(store RASPUNS 2))";
		
		String rule8="(defrule schizofrenie"+
				"(declare (salience 97))"+
				"(intrebare (stringIntrebare Auziti_sunete_sau_aveti_halucinatii?) (raspuns 1))" +
				"=>(store INTREBARE Suferiti_de_schizofrenie._Va_rugam_contactati_un_doctor)" +
				"(store RASPUNS 2))";
		
		String rule9="(defrule trepidare" +
				"(declare (salience 97))"+
				"(intrebare (stringIntrebare Auziti_sunete_sau_aveti_halucinatii?) (raspuns 0))" +
				"=>" +
				"(bind ?r (fetch RASPUNS))"+
				"(assert (intrebare (stringIntrebare Simtiti_un_tremur_neobisnuit_al_corpului?) (raspuns ?r)))" +
				"(store INTREBARE Simtiti_un_tremur_neobisnuit_al_corpului?)" +
				"(store RASPUNS 2))";
		
		String rule10="(defrule parkinson"+
				"(declare (salience 98))"+
				"(intrebare (stringIntrebare Simtiti_un_tremur_neobisnuit_al_corpului?) (raspuns 1))" +
				"=>(store INTREBARE Suferiti_de_parkinson.)" +
				"(store RASPUNS 2))";
		
		String rule11="(defrule normal"+
				"(declare (salience 98))"+
				"(intrebare (stringIntrebare Simtiti_un_tremur_neobisnuit_al_corpului?) (raspuns 0))" +
				"=>(store INTREBARE Suferiti_de_normalitate.)" +
				"(store RASPUNS 2))";

		engine.executeCommand(rule);
		engine.executeCommand(rule2);
		engine.executeCommand(rule3);
		engine.executeCommand(rule4);
		engine.executeCommand(rule5);
		engine.executeCommand(rule6);
		engine.executeCommand(rule7);
		engine.executeCommand(rule8);
		engine.executeCommand(rule9);
		engine.executeCommand(rule10);
		engine.executeCommand(rule11);

		engine.executeCommand("(facts)");
		engine.run();
		engine.retract(f);
		i++;
		System.out.println(i);
		for (int j=1; j<2*i-1; j++)
		{
			f=engine.findFactByID(j);
			if (f!=null)
				engine.retract(f);
		}

		/*ia intrebarea*/
		Value intr=engine.fetch("INTREBARE");
		if (intr!=null)
		{
			String s=intr.stringValue(engine.getGlobalContext());
			s=s.replace('_', ' ');
			rasp=2;

			if (!s.startsWith("Suferiti"))
				Interfata.setComponente(s, true);
			else
				Interfata.setComponente(s, false);
		}
		engine.executeCommand("(facts)");
	}
}