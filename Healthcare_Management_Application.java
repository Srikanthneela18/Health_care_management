package com.dev.healthcare_management;

import com.dev.healthcare_management.dao.AppointmentDao;
import com.dev.healthcare_management.dao.DoctorDao;
import com.dev.healthcare_management.dao.PatientDao;
import com.dev.healthcare_management.model.Appointment;
import com.dev.healthcare_management.model.Doctor;
import com.dev.healthcare_management.model.Patient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class HealthcareManagementApplication {
	private static Scanner scanner = new Scanner(System.in);
	private static PatientDao patientDAO = new PatientDao();
	private static DoctorDao doctorDAO = new DoctorDao();
	private static AppointmentDao appointmentDAO = new AppointmentDao();

	public static void main(String[] args) {
		SpringApplication.run(HealthcareManagementApplication.class, args);

		int choice;
		do {
			System.out.println("Healthcare Management System");
			System.out.println("1. Patient Management");
			System.out.println("2. Doctor Management");
			System.out.println("3. Appointment Management");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
				case 1:
					managePatients();
					break;
				case 2:
					manageDoctors();
					break;
				case 3:
					manageAppointments();
					break;
				case 4:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 4);
	}

	private static void managePatients() {
		int choice;
		do {
			System.out.println("Patient Management");
			System.out.println("1. Register a new patient");
			System.out.println("2. View patient details");
			System.out.println("3. Update patient information");
			System.out.println("4. Delete a patient");
			System.out.println("5. Back to main menu");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
				case 1:
					registerPatient();
					break;
				case 2:
					viewPatientDetails();
					break;
				case 3:
					updatePatient();
					break;
				case 4:
					deletePatient();
					break;
				case 5:
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		} while (true);
	}

	private static void registerPatient() {
		System.out.print("Enter patient name: ");
		String name = scanner.nextLine();
		System.out.print("Enter date of birth (YYYY-MM-DD): ");
		String dob = scanner.nextLine();
		System.out.print("Enter gender: ");
		String gender = scanner.nextLine();
		System.out.print("Enter address: ");
		String address = scanner.nextLine();

		Patient patient = new Patient();
		patient.setName(name);
		patient.setDateOfBirth(dob);
		patient.setGender(gender);
		patient.setAddress(address);

		patientDAO.registerPatient(patient);
		System.out.println("Patient registered successfully.");
	}

	private static void viewPatientDetails() {
		System.out.print("Enter patient ID: ");
		int patientId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		Patient patient = patientDAO.getPatientById(patientId);
		if (patient != null) {
			System.out.println("Patient ID: " + patient.getPatientId());
			System.out.println("Name: " + patient.getName());
			System.out.println("Date of Birth: " + patient.getDateOfBirth());
			System.out.println("Gender: " + patient.getGender());
			System.out.println("Address: " + patient.getAddress());
		} else {
			System.out.println("Patient not found.");
		}
	}

	private static void updatePatient() {
		System.out.print("Enter patient ID: ");
		int patientId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		Patient patient = patientDAO.getPatientById(patientId);
		if (patient != null) {
			System.out.print("Enter new patient name: ");
			String name = scanner.nextLine();
			System.out.print("Enter new date of birth (YYYY-MM-DD): ");
			String dob = scanner.nextLine();
			System.out.print("Enter new gender: ");
			String gender = scanner.nextLine();
			System.out.print("Enter new address: ");
			String address = scanner.nextLine();

			patient.setName(name);
			patient.setDateOfBirth(dob);
			patient.setGender(gender);
			patient.setAddress(address);

			patientDAO.updatePatient(patient);
			System.out.println("Patient updated successfully.");
		} else {
			System.out.println("Patient not found.");
		}
	}

	private static void deletePatient() {
		System.out.print("Enter patient ID: ");
		int patientId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		patientDAO.deletePatient(patientId);
		System.out.println("Patient deleted successfully.");
	}

	private static void manageDoctors() {
		int choice;
		do {
			System.out.println("Doctor Management");
			System.out.println("1. Add a new doctor");
			System.out.println("2. View doctor details");
			System.out.println("3. Update doctor information");
			System.out.println("4. Delete a doctor");
			System.out.println("5. Back to main menu");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
				case 1:
					addDoctor();
					break;
				case 2:
					viewDoctorDetails();
					break;
				case 3:
					updateDoctor();
					break;
				case 4:
					deleteDoctor();
					break;
				case 5:
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		} while (true);
	}

	private static void addDoctor() {
		System.out.print("Enter doctor name: ");
		String name = scanner.nextLine();
		System.out.print("Enter specialization: ");
		String specialization = scanner.nextLine();
		System.out.print("Enter contact number: ");
		String contactNumber = scanner.nextLine();
		System.out.print("Enter email: ");
		String email = scanner.nextLine();

		Doctor doctor = new Doctor();
		doctor.setName(name);
		doctor.setSpecialization(specialization);
		doctor.setContactNumber(contactNumber);
		doctor.setEmail(email);

		doctorDAO.addDoctor(doctor);
		System.out.println("Doctor added successfully.");
	}

	private static void viewDoctorDetails() {
		System.out.print("Enter doctor ID: ");
		int doctorId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		Doctor doctor = doctorDAO.getDoctorById(doctorId);
		if (doctor != null) {
			System.out.println("Doctor ID: " + doctor.getDoctorId());
			System.out.println("Name: " + doctor.getName());
			System.out.println("Specialization: " + doctor.getSpecialization());
			System.out.println("Contact Number: " + doctor.getContactNumber());
			System.out.println("Email: " + doctor.getEmail());
		} else {
			System.out.println("Doctor not found.");
		}
	}

	private static void updateDoctor() {
		System.out.print("Enter doctor ID: ");
		int doctorId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		Doctor doctor = doctorDAO.getDoctorById(doctorId);
		if (doctor != null) {
			System.out.print("Enter new doctor name: ");
			String name = scanner.nextLine();
			System.out.print("Enter new specialization: ");
			String specialization = scanner.nextLine();
			System.out.print("Enter new contact number: ");
			String contactNumber = scanner.nextLine();
			System.out.print("Enter new email: ");
			String email = scanner.nextLine();

			doctor.setName(name);
			doctor.setSpecialization(specialization);
			doctor.setContactNumber(contactNumber);
			doctor.setEmail(email);

			doctorDAO.updateDoctor(doctor);
			System.out.println("Doctor updated successfully.");
		} else {
			System.out.println("Doctor not found.");
		}
	}

	private static void deleteDoctor() {
		System.out.print("Enter doctor ID to delete: ");
		int doctorId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		doctorDAO.deleteDoctor(doctorId);
		System.out.println("Doctor deleted successfully.");
	}

	private static void manageAppointments() {
		int choice;
		do {
			System.out.println("Appointment Management");
			System.out.println("1. Schedule a new appointment");
			System.out.println("2. View appointment details");
			System.out.println("3. Update appointment information");
			System.out.println("4. Cancel an appointment");
			System.out.println("5. Back to main menu");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
				case 1:
					scheduleAppointment();
					break;
				case 2:
					viewAppointmentDetails();
					break;
				case 3:
					updateAppointment();
					break;
				case 4:
					cancelAppointment();
					break;
				case 5:
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		} while (true);
	}

	private static void scheduleAppointment() {
		System.out.print("Enter patient ID: ");
		int patientId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter doctor ID: ");
		int doctorId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter appointment date (YYYY-MM-DD): ");
		String date = scanner.nextLine();
		System.out.print("Enter appointment time (HH:MM): ");
		String time = scanner.nextLine();

		Appointment appointment = new Appointment();
		appointment.setAppointmentId(patientId);
		appointment.setDoctorId(doctorId);
		appointment.setAppointmentDate(date);
		appointment.setAppointmentTime(time);
		appointment.setStatus("scheduled");

		appointmentDAO.scheduleAppointment(appointment);
		System.out.println("Appointment scheduled successfully.");
	}

	private static void viewAppointmentDetails() {
		System.out.print("Enter appointment ID: ");
		int appointmentId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
		if (appointment != null) {
			System.out.println("Appointment ID: " + appointment.getAppointmentId());
			System.out.println("Patient ID: " + appointment.getPatientId());
			System.out.println("Doctor ID: " + appointment.getDoctorId());
			System.out.println("Appointment Date: " + appointment.getAppointmentDate());
			System.out.println("Appointment Time: " + appointment.getAppointmentTime());
			System.out.println("Status: " + appointment.getStatus());
		} else {
			System.out.println("Appointment not found.");
		}
	}

	private static void updateAppointment() {
		System.out.print("Enter appointment ID: ");
		int appointmentId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
		if (appointment != null) {
			System.out.print("Enter new patient ID: ");
			int patientId = scanner.nextInt();
			scanner.nextLine(); // Consume newline
			System.out.print("Enter new doctor ID: ");
			int doctorId = scanner.nextInt();
			scanner.nextLine(); // Consume newline
			System.out.print("Enter new appointment date (YYYY-MM-DD): ");
			String appointmentDate = scanner.nextLine();
			System.out.print("Enter new appointment time: ");
			String appointmentTime = scanner.nextLine();
			System.out.print("Enter new status (scheduled/cancelled): ");
			String status = scanner.nextLine();

			appointment.setAppointmentId(patientId);
			appointment.setDoctorId(doctorId);
			appointment.setAppointmentDate(appointmentDate);
			appointment.setAppointmentTime(appointmentTime);
			appointment.setStatus(status);

			appointmentDAO.updateAppointment(appointment);
			System.out.println("Appointment updated successfully.");
		} else {
			System.out.println("Appointment not found.");
		}
	}

	private static void cancelAppointment() {
		System.out.print("Enter appointment ID to cancel: ");
		int appointmentId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		appointmentDAO.cancelAppointment(appointmentId);
		System.out.println("Appointment cancelled successfully.");
	}

}
