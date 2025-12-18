# Copilot / AI Agent Instructions for Java_project

This repo is a minimal Java/Swing desktop app. The goal of this file is to capture project-specific patterns and actionable guidance so AI coding agents can be productive immediately.

- **Big picture:**
  - Two main packages: `backend` (simple model classes) and `GUI` (Swing windows). The app entrypoint is [Main.java](Main.java#L1).
  - UI classes create `JFrame` instances inside constructors (see [GUI/HomeWindow.java](GUI/HomeWindow.java#L1) and [GUI/LoginWindow.java](GUI/LoginWindow.java#L1)).
  - Backend classes contain only fields and placeholder methods (e.g., [backend/Register.java](backend/Register.java#L1) and [backend/AddActivity.java](backend/AddActivity.java#L1)). Data flow from GUI → backend is not implemented; GUI currently only constructs components.

- **Key patterns and conventions (project-specific):**
  - Package names: `backend` (lowercase) and `GUI` (uppercase). Note: `GUI` being uppercase is a project convention and deviates from typical Java style — preserve it when modifying imports and packages.
  - UI code places components directly on the frame without explicit layout managers (Relies on default `FlowLayout`). Keep changes consistent with this approach unless migrating all windows to a layout manager.
  - Event handling is not implemented yet. Buttons are created but lack `ActionListener`s (see [GUI/LoginWindow.java](GUI/LoginWindow.java#L1)). Implement listeners inside the same window class or add controller classes — be explicit about where the callback should be placed.

- **Where to make changes / integration points:**
  - Hook GUI actions to backend in window classes. Example: when RegisterWindow's OK button is pressed, construct a `backend.Register` object and call its `ok()` method (see [GUI/RegisterWindow.java](GUI/RegisterWindow.java#L1) and [backend/Register.java](backend/Register.java#L1)).
  - Persistence/storage is not present. If adding file/DB support, create a new `backend` class (e.g., `backend/Storage.java`) and call it from `ok()` methods.

- **Build / run (discoverable):**
  - No build tool provided (no Maven/Gradle). Use `javac`/`java` from project root. Example to compile and run from repo root:

    - Compile all .java files into `bin`:

      javac -d bin $(find . -name "*.java")

      (On Windows PowerShell, use `Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }` or compile by directory.)

    - Run the app (from project root, with `bin` on classpath):

      java -cp bin Main

  - If adding a build tool, update README.md with the new commands.

- **Quick examples an agent can act on now:**
  - Add `ActionListener` to `GUI/LoginWindow.java` `okButton` to read `user`/`password` and call a new `backend.Auth.authenticate(user, pass)` method.
  - Implement `backend/Register.ok()` to accept field values (name, first name, mail, club) and return a success boolean; update `GUI/RegisterWindow.java` to call it and display a `JOptionPane` result.

- **Safety/consistency rules for edits:**
  - Preserve package declarations exactly (including `package GUI;`) to avoid breaking imports.
  - Keep UI construction inside the existing window classes unless you refactor all windows consistently.
  - Avoid introducing new external dependencies without updating the README and adding a build manifest (Maven `pom.xml` or Gradle `build.gradle`).

- **Files to inspect when working on features:**
  - App entry: [Main.java](Main.java#L1)
  - GUI: [GUI/HomeWindow.java](GUI/HomeWindow.java#L1), [GUI/LoginWindow.java](GUI/LoginWindow.java#L1), [GUI/RegisterWindow.java](GUI/RegisterWindow.java#L1), [GUI/AddActivityWindow.java](GUI/AddActivityWindow.java#L1)
  - Backend models: [backend/Register.java](backend/Register.java#L1), [backend/Login.java](backend/Login.java#L1), [backend/AddActivity.java](backend/AddActivity.java#L1)

If any part of the codebase or workflow is unclear, tell me which file or command you want clarified and I will update this guidance.
