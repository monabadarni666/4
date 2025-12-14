# Ex4 – Main OOP Assignment (Geometric Shapes + GUI + Save/Load)
**Introduction to Computer Science – Ariel University (2022)**

## 📌 Overview
This repository contains my solution for **Exercise 4 (Ex4)** in the *Introduction to Computer Science* course (Ariel University, 2022).

The project implements:
- A set of **2D geometric shapes** (OOP design + interfaces)
- A **shape collection/container**
- A basic **GUI** for drawing, selecting, and interacting with shapes
- **Save/Load** support for storing and restoring drawings from files
- A **JUnit test suite** for geometry, collections, and persistence  
  *(GUI classes are tested manually as required by the assignment.)*

---

## 📂 Repository Structure
.
├── geo/ # Geometric classes (Point2D, Circle2D, Rect2D, Segment2D, Triangle2D, Polygon2D, etc.)
├── gui/ # GUI implementation classes
├── test/ # JUnit tests (Geo/Collections/Save&Load)
├── Ex4Main.java # Main entry point (runs the GUI / demos)
├── ShapeCollection.java # Implementation of the shape container
├── ShapeCollectionable.java # Container interface
├── GUIShape.java # GUI shape wrapper/implementation
├── GUI_Shapeable.java # GUI shape interface
├── Ex4_Const.java # Shared constants (colors, modes, etc.)
├── StdDraw_Ex4.java # Drawing utilities
├── a0, a1, a2 # Example input files for load demonstration
├── testSave.txt / testLoad.txt# Example files used for save/load testing
└── Ex4_sol.V0.1-out.jar # Reference solution (provided by course staff)

---

## 🧠 Implemented Geometry (geo/)
The `geo/` package contains implementations of the required shapes:
- `Point2D`
- `Circle2D`
- `Rect2D`
- `Segment2D`
- `Triangle2D`
- `Polygon2D` (simple polygon assumption — no self-intersections)

Each shape supports the standard API defined in the assignment (e.g., `area()`, `perimeter()`, `contains(Point2D)`, `move(...)`, `copy()`, etc.).

---

## 🧺 Shape Collection
`ShapeCollection` implements a container for shapes and supports operations such as:
- Add / Remove shapes
- Iterate and access shapes
- Copy / Move
- Save to file / Load from file

---

## 🖼️ GUI
The GUI allows interactive work with shapes:
- Draw and display shapes
- Select shapes and apply operations
- Load drawings from files (e.g., `a0`, `a1`, `a2`)
- Save the current drawing to a file

> GUI classes are not required to have JUnit tests (tested manually).

---

## 🧪 Testing (JUnit)
The `test/` package includes unit tests for:
- Geometric correctness (area/perimeter/contains)
- Collection behavior
- Save & Load functionality (including provided test files)

To run tests:
- Open the project in IntelliJ / Eclipse
- Run the JUnit test classes under `test/`

---

## ▶️ How to Run

### Option 1 — Run from IDE (recommended)
1. Open the project in IntelliJ / Eclipse
2. Run `Ex4Main.java`
3. The GUI window will open

### Option 2 — Run reference solution (for comparison)
```bash
java -jar Ex4_sol.V0.1-out.jar
Try loading a0 and a2 to compare the expected output.
