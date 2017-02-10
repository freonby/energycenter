package by.qlab.energycenter.datamodel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import by.qlab.energycenter.dao.DAO;

public class Test {

	public static void initCustomerBase(DAO dao) {

		Customer customer = new Customer();

		CurrentTransformer ctt1 = new CurrentTransformer();
		CurrentTransformer ctt2 = new CurrentTransformer();
		ctt1.setName("ТОП-0,66");
		ctt1.setKI(200);
		ctt2.setName("ТОП-0,66М");
		ctt2.setKI(160);

		VoltageTransformer utt1 = new VoltageTransformer();
		VoltageTransformer utt2 = new VoltageTransformer();
		utt1.setName("ЗНОЛ-1");
		utt2.setName("ЗНОЛ-2");
		utt1.setKU(5000);
		utt2.setKU(10000);

		EnergyMeterType emt1 = new EnergyMeterType();
		emt1.setType("CC301-5.1");
		EnergyMeterType emt2 = new EnergyMeterType();
		emt2.setType("CC301-10.1");

		EnergyMeter em1 = new EnergyMeter();
		EnergyMeter em2 = new EnergyMeter();

		em1.setEnergymeterType("CC301-5.1");

		em2.setEnergymeterType("CC301-10.1");

		Fider f1 = new Fider();
		Fider f2 = new Fider();
		f1.setCurrentTransformer(ctt1);
		ctt1.setFider(f1);
		f1.setVoltageTransformer(utt1);
		utt1.setFider(f1);
		f1.setEnergyMeter(em1);
		em1.setFider(f1);
		f1.setName("яч.510");
		f2.setCurrentTransformer(ctt2);
		ctt2.setFider(f2);
		f2.setVoltageTransformer(utt2);
		utt2.setFider(f2);
		f2.setEnergyMeter(em2);
		em2.setFider(f2);
		f2.setName("яч.516");

		SectionBus sb = new SectionBus();
		sb.setName("СШ-1");
		sb.getListFiders().add(f1);
		f1.setSectionBus(sb);
		sb.getListFiders().add(f2);
		f2.setSectionBus(sb);

		ElectricalBus ebus = new ElectricalBus();
		ebus.setVoltage("10кВ");
		ebus.getListSections().add(sb);
		sb.setElectricalBus(ebus);

		TransformerStation ts = new TransformerStation();
		ts.getListElectricalBusses().add(ebus);
		ebus.setTransformerStation(ts);
		ts.setName("ТП-2634");

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
		Customer c = h.getCustomer(name);

		h.deleteCustomer(c);

	}

	public static void getCustomer(DAO h, String name) {
		Customer c = h.getCustomer("ЖМС");
		TransformerStation ts = c.getListStations().get(0);
		System.out.println("Подстанция: " + ts.getName());
		ElectricalBus eb = ts.getListElectricalBusses().get(0);
		System.out.println("Шина: " + eb.getVoltage());
		SectionBus sb = eb.getListSections().get(0);
		System.out.println("Секция: " + sb.getName());
		System.out.println("Фидеры: ");
		List<Fider> flist = sb.getListFiders();
		if (!flist.isEmpty()) {
			for (Fider fider : flist) {
				System.out.println("Фидер: " + fider.getName());
				System.out.println("Счетчик: " + fider.getEnergyMeter().getEnergymeterType());
				System.out.println("Трансформатор тока: " + fider.getCurrentTransformer().getName());
				System.out.println("КТТ: " + fider.getCurrentTransformer().getKI());
				System.out.println("Трансформатор напряжения: " + fider.getVoltageTransformer().getName());
				System.out.println("КU: " + fider.getVoltageTransformer().getKU());
				System.out.println("=========================================");

			}
		}

	}

}
