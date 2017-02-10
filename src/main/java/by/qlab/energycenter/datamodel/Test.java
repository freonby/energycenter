package by.qlab.energycenter.datamodel;

import java.util.HashSet;
import java.util.Set;

import by.qlab.energycenter.dao.DAO;

public class Test {

	public static void initCustomerBase(DAO dao) {

		Customer customer = new Customer();

		UTType ut1 = new UTType();
		UTType ut2 = new UTType();
		ut1.setType("ЗНОЛ-1");
		ut2.setType("ЗНОЛ-2");

		TTType it1 = new TTType();
		TTType it2 = new TTType();
		it1.setType("TOП-0.66");
		it2.setType("TOП-M-0.66");

		CurrentTransformer ctt1 = new CurrentTransformer();
		CurrentTransformer ctt2 = new CurrentTransformer();
		ctt1.getListCurTransformerTypes().add(it1);
		it1.setCurrentTransformer(ctt1);
		ctt2.getListCurTransformerTypes().add(it2);
		it2.setCurrentTransformer(ctt2);

		VoltageTransformer utt1 = new VoltageTransformer();
		VoltageTransformer utt2 = new VoltageTransformer();
		utt1.getListVoltTransformerTypes().add(ut1);
		ut1.setVoltageTransformer(utt1);
		utt2.getListVoltTransformerTypes().add(ut2);
		ut2.setVoltageTransformer(utt2);

		EnergyMeterType emt1 = new EnergyMeterType();
		emt1.setType("CC301-5.1");
		EnergyMeterType emt2 = new EnergyMeterType();
		emt2.setType("CC301-10.1");

		EnergyMeter em1 = new EnergyMeter();
		EnergyMeter em2 = new EnergyMeter();

		em1.getListTypes().add(emt1);
		emt1.setEnergyMeter(em1);
		em2.getListTypes().add(emt2);
		emt2.setEnergyMeter(em2);

		Fider f1 = new Fider();
		Fider f2 = new Fider();
		f1.getListCurrentTransformers().add(ctt1);
		ctt1.setFider(f1);
		f1.getListVoltageTransformers().add(utt1);
		utt1.setFider(f1);
		f1.getListEnergyMeters().add(em1);
		em1.setFider(f1);
		f1.setName("яч.510");
		f2.getListCurrentTransformers().add(ctt2);
		ctt2.setFider(f2);
		f2.getListVoltageTransformers().add(utt2);
		utt2.setFider(f2);
		f2.getListEnergyMeters().add(em2);
		em2.setFider(f2);
		f2.setName("яч.516");

		SectionBus sb = new SectionBus();
		sb.setName("СШ-1");
		sb.getListFiders().add(f1);
		f1.setSectionBus(sb);
		sb.getListFiders().add(f2);
		f2.setSectionBus(sb);

		Voltage voltage = new Voltage();
		voltage.setVoltage("0.4 кВ");

		ElectricalBus ebus = new ElectricalBus();
		ebus.getListVoltages().add(voltage);
		voltage.setElectricalBus(ebus);
		ebus.getListSections().add(sb);
		sb.setElectricalBus(ebus);

		StationType stype = new StationType();
		stype.setTypeStation("ТП");

		TransformerStation ts = new TransformerStation();
		ts.getListTypes().add(stype);
		stype.setTransformerStation(ts);
		ts.getListElectricalBusses().add(ebus);
		ebus.setTransformerStation(ts);
		ts.setName("ТП-26 Цех");

		customer.getListStations().add(ts);
		ts.setCustomer(customer);
		customer.setCompanyName("ЖМС");
		customer.setAddress("г.Жлобин");
		dao.addCustomer(customer);

	}

	public static void initUser(DAO h, String name) {
		User u = new User();
		u.setUserLogin(name);
		Role r1 = new Role();
		Role r2 = new Role();
		Role r3 = new Role();
		r1.setRole("admin");
		r2.setRole("user");
		r3.setRole("anonym");
		r1.setUser(u);
		r2.setUser(u);
		r3.setUser(u);
		Set<Role> rset = new HashSet<Role>();
		rset.add(r1);
		rset.add(r2);
		rset.add(r3);
		u.getRoles().addAll(rset);

		h.addUser(u);

	}

	public static void changeUser(DAO h, String name) {
		User u = h.getUser(1);
		u.setUserLogin(name);

		h.addUser(u);

	}

	public static void initCustomer(DAO h, String name) {
		Customer c = new Customer();
		c.setCompanyName(name);
		c.setAddress("г.Могилев");
		h.addCustomer(c);

	}

	public static void addTransStation(DAO h, String name) {
		Customer c = h.getCustomer("Строммашина");
		TransformerStation ts = new TransformerStation();
		ts.setName(name);
		ts.setCustomer(c);
		c.getListStations().add(ts);
		h.addCustomer(c);

	}

	public static void addElectricalBus(DAO h, String name) {
		Customer c = h.getCustomer("Строммашина");
		TransformerStation ts = c.getListStations().iterator().next();
		ElectricalBus eb = new ElectricalBus();
		eb.setTransformerStation(ts);
		c.getListStations().iterator().next().getListElectricalBusses().add(eb);
		h.addCustomer(c);

	}

	public static void delCustomer(DAO h, String name) {
		Customer c = h.getCustomer("Строммашина");

		h.deleteCustomer(c);

	}

}
