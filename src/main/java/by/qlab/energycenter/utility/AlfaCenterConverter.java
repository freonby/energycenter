package by.qlab.energycenter.utility;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import by.qlab.energycenter.dao.DAO;
import by.qlab.energycenter.databuffer.Register;
import by.qlab.energycenter.datamodel.CurrentTransformer;
import by.qlab.energycenter.datamodel.Customer;
import by.qlab.energycenter.datamodel.ElectricalBus;
import by.qlab.energycenter.datamodel.EnergyMeter;
import by.qlab.energycenter.datamodel.Fider;
import by.qlab.energycenter.datamodel.SectionBus;
import by.qlab.energycenter.datamodel.TransformerStation;
import by.qlab.energycenter.datamodel.VoltageTransformer;

public class AlfaCenterConverter {

	public static List<Register> createCustomerById(long id_object) {
		Customer customer = new Customer();
		TransformerStation ts = new TransformerStation();
		List<TransformerStation> tsList = new ArrayList<TransformerStation>();
		List<Register> list = null;
		String sql = "SELECT TXT FROM OBEKT WHERE N_OB='" + id_object + "'";
		String find = "";
		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/energy_base", "user2013264", "Zx1976bd");
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				find = rs.getString("TXT");
				System.out.println("Найден объект: " + find);
				ts.setName("Подстанция " + find);
				tsList.add(ts);
				customer.setCompanyName(find);
				System.out.println("Создан заказчик: " + customer.getCompanyName());
				ts.setCustomer(customer);
				customer.setListStations(tsList);
				System.out.println("Добавлена подстанция: " + ts.getName());

			}
			customer = findElectricalBus(conn, id_object, customer);
			customer = findSections(conn, id_object, customer);
			customer = findFiders(conn, id_object, customer);
			customer = findMeters(conn, id_object, customer);
			list = fillRegister(conn, customer, "01.02.2017", "07.02.2017", id_object);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (st != null)
					st.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}

	public static Customer findElectricalBus(Connection conn, long id_object, Customer customer) {
		List<TransformerStation> list = customer.getListStations();
		TransformerStation ts = list.get(0);
		String find = "";
		ElectricalBus ebus = new ElectricalBus();
		List<ElectricalBus> ebList = new ArrayList<ElectricalBus>();

		String sql = "SELECT KL_NAPR FROM HIN WHERE N_OB='" + id_object + "'";
		ResultSet rs2 = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				find = rs.getString("KL_NAPR");
				System.out.println("Найден тип класса: " + find);
			}
			sql = "SELECT TXT FROM KL_NAPR WHERE KL_NAPR='" + find + "'";
			rs2 = st.executeQuery(sql);
			while (rs2.next()) {
				find = rs2.getString("TXT");
			}
			ebus.setVoltage(find);
			ebus.setTransformerStation(ts);
			ebList.add(ebus);
			System.out.println("Добавлена шина: " + ebus.getVoltage());
			ts.setListElectricalBusses(ebList);
			list.clear();
			System.out.println("Очистка списка подстанций");
			list.add(ts);
			customer.setListStations(list);
			System.out.println("Добавлено в объект Заказчика");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				rs2.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return customer;
	}

	public static Customer findSections(Connection conn, long id_object, Customer customer) {
		List<TransformerStation> stationList = customer.getListStations();
		List<ElectricalBus> ebList = stationList.get(0).getListElectricalBusses();
		ElectricalBus ebus = ebList.get(0);
		List<SectionBus> sbList = new ArrayList<SectionBus>();
		String sql = "SELECT N_SHINY,TXT FROM SHIN WHERE N_OB='" + id_object + "'";
		Statement st = null;
		ResultSet rs = null;
		SectionBus sb = null;
		try {
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				String name = rs.getString("TXT");
				// String num = rs.getString("N_SHINY");
				System.out.println("Найдена секция шин: " + name);
				sb = new SectionBus();
				sb.setName(name);
				// sb.setSectionbus_id(Long.parseLong(num));
				System.out.println("Создана секция шин: " + sb.getName());
				sb.setElectricalBus(ebus);
				sbList.add(sb);
				System.out.println("Добавлено в список: " + sb.getName());

			}
			ebus.setListSections(sbList);
			ebList.clear();
			System.out.println("Очистка списка шины");
			ebList.add(ebus);
			System.out.println("Добавлена шина с секциями");
			stationList.get(0).setListElectricalBusses(ebList);
			System.out.println("Добавлена шина в подстанцию");
			customer.setListStations(stationList);
			System.out.println("Добавлено в объект Заказчика");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();

				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return customer;
	}

	public static Customer findFiders(Connection conn, long id_object, Customer customer) {
		List<TransformerStation> stationList = customer.getListStations();
		List<ElectricalBus> ebList = stationList.get(0).getListElectricalBusses();

		String sql = "SELECT N_SHINY,TXT FROM SHIN WHERE N_OB='" + id_object + "'";

		Statement st = null;
		ResultSet rs = null;
		ArrayList<String[]> listNumbersShin = new ArrayList<String[]>();

		// Find shin id
		try {
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				// Retrieve by column name
				String num = rs.getString("N_SHINY");
				String nameSection = rs.getString("TXT");
				System.out.println("Найдена секция: " + nameSection + " - " + num);
				String[] s = new String[2];
				s[0] = num;
				s[1] = nameSection;
				listNumbersShin.add(s);

			}

			List<SectionBus> newList = new ArrayList<SectionBus>();

			for (String[] shina : listNumbersShin) {
				sql = "SELECT TXT,N_FID,KT_1,KT_2,KN_1,KN_2 FROM FID WHERE N_SHINY='" + shina[0] + "' and N_OB='" + id_object + "'";
				rs = st.executeQuery(sql);
				SectionBus sb = ebList.get(0).getSectionBusByName(shina[1]);
				System.out.println("Выбрана секция: ======================" + sb.getName());
				List<Fider> fiderList = new ArrayList<Fider>();
				while (rs.next()) {
					// Retrieve by column name
					String fiderName = rs.getString("TXT");
					int kt1 = rs.getInt("KT_1");
					int kt2 = rs.getInt("KT_2");
					int kn1 = rs.getInt("KN_1");
					int kn2 = rs.getInt("KN_2");
					CurrentTransformer ct = new CurrentTransformer();
					ct.setKI(kt1 / kt2);
					ct.setName("ТТ");
					VoltageTransformer vt = new VoltageTransformer();
					vt.setKU(kn1 / kn2);
					vt.setName("ТН");
					System.out.println("Найден фидер: " + fiderName);
					Fider fider = new Fider();
					fider.setName(fiderName);
					ct.setFider(fider);
					vt.setFider(fider);
					fider.setCurrentTransformer(ct);
					fider.setVoltageTransformer(vt);
					fider.setSectionBus(sb);
					fiderList.add(fider);
					System.out.println("Добавлен фидер: " + fider.getName() + " в секцию " + shina[1]);
				}

				sb.setListFiders(fiderList);

				System.out.println("Секция шин: " + sb.getName());
				System.out.println(sb);

				newList.add(sb);
				sb = null;
			}

			ebList.get(0).setListSections(newList);
			System.out.println("Добавлен новый список секций");
			stationList.get(0).setListElectricalBusses(ebList);
			System.out.println("Добавлено в подстанции");
			System.out.println(ebList.get(0));
			customer.setListStations(stationList);
			System.out.println("Добавлено в объект Заказчика");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();

				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return customer;
	}

	public static Customer findMeters(Connection conn, long id_object, Customer customer) {
		List<TransformerStation> stationList = customer.getListStations();
		List<ElectricalBus> ebList = stationList.get(0).getListElectricalBusses();
		List<SectionBus> sbList = ebList.get(0).getListSections();
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<Fider> newFiderList = new ArrayList<Fider>();
		List<SectionBus> newSectionList = new ArrayList<SectionBus>();
		try {
			st = (Statement) conn.createStatement();

			for (SectionBus sbus : sbList) {

				List<Fider> fiderList = sbus.getListFiders();

				for (Fider fid : fiderList) {
					sql = "SELECT N_FID FROM FID WHERE TXT='" + fid.getName() + "' and N_OB='" + id_object + "'";
					rs = st.executeQuery(sql);
					String num = "";
					while (rs.next()) {
						// Retrieve by column name
						num = rs.getString("N_FID");
					}
					sql = "SELECT N_SH,TXT FROM SH WHERE N_FID='" + num + "' and N_OB='" + id_object + "'";
					rs2 = st.executeQuery(sql);
					while (rs2.next()) {
						String number = rs2.getString("N_SH");
						String type = rs2.getString("TXT");
						EnergyMeter em = new EnergyMeter();
						em.setNumber(number);
						em.setEnergymeterType(type);
						em.setFider(fid);
						fid.setEnergyMeter(em);
						newFiderList.add(fid);

					}

				}

				sbus.setListFiders(newFiderList);
				System.out.println("ПРОВЕРКА: СЕКЦИЯ " + sbus.getName());
				System.out.println(sbus);
				newSectionList.add(sbus);

				newFiderList = new ArrayList<Fider>();

			}
			System.out.println("ПРОВЕРКА: НОВЫЙ ЛИСТ СЕКЦИЙ");
			for (SectionBus sectionBus : newSectionList) {
				System.out.println(sectionBus);
			}
			System.out.println("ПРОВЕРКА: ШИНЫ");
			ebList.get(0).setListSections(newSectionList);
			System.out.println(ebList.get(0));
			System.out.println("Добавлен новый список секций");
			stationList.get(0).setListElectricalBusses(ebList);
			System.out.println("Добавлено в подстанции");
			customer.setListStations(stationList);
			System.out.println("Добавлено в объект Заказчика");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				rs2.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return customer;
	}

	// launch after adding databuffers in database!
	public static void fixEnergyMetersId(DAO hdao, Customer customer) {
		List<TransformerStation> list = customer.getListStations();
		for (TransformerStation stList : list) {
			for (ElectricalBus ebList : stList.getListElectricalBusses()) {
				for (SectionBus sbList : ebList.getListSections()) {
					for (Fider fider : sbList.getListFiders()) {
						long fider_id = fider.getFider_id();

					}
				}

			}

		}
	}

	public static List<Register> fillRegister(Connection conn, Customer customer, String start_DD_MM_YYYY, String end_DD_MM_YYYY, long id_object) {
		List<Register> register = new ArrayList<Register>();
		List<TransformerStation> list = customer.getListStations();
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		try {
			st = (Statement) conn.createStatement();
			for (TransformerStation stList : list) {
				for (ElectricalBus ebList : stList.getListElectricalBusses()) {
					for (SectionBus sbList : ebList.getListSections()) {
						for (Fider fider : sbList.getListFiders()) {
							EnergyMeter em = fider.getEnergyMeter();
							System.out.println("ПРОВЕРКА ID: " + fider.getFider_id());
							sql = "SELECT N_GR_TY,DD_MM_YYYY,N_INTER_RAS,POK_START,RASH_POLN FROM BUF_V_INT WHERE N_SH='" + em.getNumber() + "' and N_OB='" + id_object + "' and DD_MM_YYYY between '" + start_DD_MM_YYYY + "' and '" + end_DD_MM_YYYY
									+ "'";
							rs = st.executeQuery(sql);

							while (rs.next()) {
								// Retrieve by column name

								int typeTY = rs.getInt("N_GR_TY");
								String date = rs.getString("DD_MM_YYYY");
								int intervalNumber = rs.getInt("N_INTER_RAS");
								double consumption = Double.valueOf((rs.getString("RASH_POLN")).replace(",", "."));
								double readout = Double.valueOf((rs.getString("POK_START")).replace(",", "."));
								switch (typeTY) {
								case 1:
									Register ap = new Register();
									ap.setDate(ap.dateConverter(date));
									ap.setIntervalNumber(intervalNumber);
									ap.setConsumption(consumption);
									ap.setReadout(readout);
									ap.setEnergyMeter_number(em.getNumber());
									ap.setEnergyType(typeTY);
									register.add(ap);
									break;
								case 2:
									Register am = new Register();
									am.setDate(am.dateConverter(date));
									am.setIntervalNumber(intervalNumber);
									am.setConsumption(consumption);
									am.setReadout(readout);
									am.setEnergyMeter_number(em.getNumber());
									am.setEnergyType(typeTY);
									register.add(am);
									break;
								case 3:
									Register rp = new Register();
									rp.setDate(rp.dateConverter(date));
									rp.setIntervalNumber(intervalNumber);
									rp.setConsumption(consumption);
									rp.setReadout(readout);
									rp.setEnergyMeter_number(em.getNumber());
									rp.setEnergyType(typeTY);
									register.add(rp);
									break;
								case 4:
									Register rm = new Register();
									rm.setDate(rm.dateConverter(date));
									rm.setIntervalNumber(intervalNumber);
									rm.setConsumption(consumption);
									rm.setReadout(readout);
									rm.setEnergyMeter_number(em.getNumber());
									rm.setEnergyType(typeTY);
									register.add(rm);
									break;
								default:
									break;

								}

							}

						}
					}

				}

			}
			System.out.println("Получен буфер, записей: " + register.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return register;
	}

}
