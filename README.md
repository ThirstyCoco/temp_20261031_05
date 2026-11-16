# PROG2004 Assessment 1 – Health Service Appointment System


## What this program does

This project models the backbone of an appointment system for a health service. It records the
health professionals who work at the clinic, the patients who book with them, and the appointments
made for the current day. There is no user interface: the program shows what the classes can do by
running a scripted demonstration in `AssignmentOne.main`.

## Project structure

```
temp_20261031_05/
├── src/
│   ├── AssignmentOne.java           entry point; one demonstration per assessment part
│   ├── HealthProfessional.java      abstract base class for every professional
│   ├── GeneralPractitioner.java     can issue prescriptions
│   ├── Specialist.java              can order diagnostic tests
│   ├── Nurse.java                   can take blood samples
│   ├── Dietitian.java               can create meal plans
│   ├── Patient.java                 patient name and mobile number
│   ├── Appointment.java             links a patient, a professional and a time; Comparable
│   └── AppointmentManager.java      stores, filters, sorts and cancels appointments
├── .gitignore
└── README.md
```

## How the code maps to the assessment parts

| Part | Where to look | What it demonstrates |
|---|---|---|
| 1 – Modelling a health professional | `HealthProfessional` | private fields, two constructors, validation, defensive copy, `toString`, `equals`, `hashCode`, abstract `getRole()` |
| 2 – Specialising health professionals | `GeneralPractitioner`, `Specialist`, `Nurse`, `Dietitian` | inheritance, `super`, method overriding, one type-specific behaviour each |
| 3 – Working with the objects | `AssignmentOne` Part 3 section | a `List<HealthProfessional>` holding all four types, printed with one loop |
| 4 – Modelling an appointment | `Patient`, `Appointment` | a patient is its own class; an appointment works with any professional type and implements `Comparable` |
| 5 – Managing the collection | `AppointmentManager` | fixed time slots, booking with double-booking check, filtering, sorting, cancelling |
| 6 – Working with appointments | `AssignmentOne` Part 6 sections | the full workflow plus the error and edge cases |

## How to run

**In Visual Studio Code:** open the project folder, open `src/AssignmentOne.java`, then click the
Run button above `main` (or press `F5`).

**From a terminal:**

```
cd src
javac *.java
java AssignmentOne
```

## Design notes

- **Encapsulation** – every field is `private` and `final`; arrays are copied both when received and
  when returned, so outside code cannot change an object's internal state.
- **Inheritance** – the four professional types extend `HealthProfessional`, so the shared fields,
  validation and `toString` are written once.
- **Abstraction** – `HealthProfessional` is abstract and declares `getRole()`, so a generic
  professional can never be created and every subclass must identify itself.
- **Polymorphism** – `toString()` calls `getRole()`, so each type prints differently, and the
  `List<HealthProfessional>` is processed with a single loop that needs no type checks.
- **Ordering** – `Appointment` implements `Comparable`, so `Collections.sort()` can order a day's
  appointments without any custom sorting code. Time slots are stored as four digit strings
  (`"0930"`), which sort chronologically.
- **Identity** – `HealthProfessional.equals` uses the id and `Patient.equals` uses the phone number,
  so two objects that represent the same person are treated as equal.

## Validation approach

Assessment 1 covers Modules 1–3, which do not include exception handling. Invalid values therefore
print a clear warning and set the object's `isValid()` flag to `false`. `AppointmentManager` checks
that flag before booking, so an invalid patient or professional can‘t enter the collection.


