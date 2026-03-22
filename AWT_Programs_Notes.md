# AWT Programs (Scrollbar + Checkbox): Detailed Explanation, Concepts, and Controls

This markdown explains two AWT programs:
- `ScrollBarExample.java` — demonstrates an AWT `Scrollbar` and `AdjustmentListener`.
- `CheckBoxExample.java` — demonstrates AWT `Checkbox` controls and `ItemListener`.

It also includes the core concepts behind AWT controls, layouts, and event handling.

---

## AWT Basics (Concepts You’re Using)

### What is AWT?
**AWT (Abstract Window Toolkit)** is Java’s original GUI toolkit (`java.awt.*`). It provides:
- **Top-level windows** like `Frame`
- **Controls/components** like `Label`, `Checkbox`, `Scrollbar`
- **Layout managers** to place components
- **Event handling** (listeners + events) to react to user actions

### Container vs Component
- **Component**: A UI control that can be displayed (e.g., `Label`, `Checkbox`, `Scrollbar`, `Canvas`).
- **Container**: A component that can hold other components (e.g., `Frame`, `Panel`).

### Layout Managers (How components are arranged)
AWT uses layout managers to avoid manual pixel positioning.
- `FlowLayout`: places components left-to-right, wrapping to the next line.
- `BorderLayout`: divides the window into 5 regions: `NORTH`, `SOUTH`, `EAST`, `WEST`, `CENTER`.

### Event-Driven Programming (Most important concept)
GUI code is **event-driven**:
- The program does not “continuously” read inputs.
- Instead, it **registers listeners**.
- When user performs an action (click, scroll), Java fires an **event**.
- Your listener method runs automatically.

General pattern:
1. Create component
2. Register listener (`addXxxListener(...)`)
3. Implement callback method (`xxxChanged(...)`, `itemStateChanged(...)`, etc.)

---

## Program 1: `ScrollBarExample.java` (Scrollbar + AdjustmentListener)

### What this program does
It creates an AWT window with a **vertical scrollbar**. When you move the scrollbar:
- The current value is read from the event
- The window title is updated to show the value
- (In the enhanced version) a `Label` and a `Canvas` preview also update

### Controls/components used (and why)
- **`Frame`**: top-level window (the app window).
- **`Scrollbar`**: lets the user choose a numeric value by scrolling.
- **`Label`** *(enhanced version)*: shows the current value inside the window.
- **`Canvas`** *(enhanced version)*: draws a simple preview that changes when the value changes.
- **`Panel`** *(enhanced version)*: a container used to group components inside the frame.

### Important API concepts used

#### 1) `Scrollbar` orientation
`Scrollbar.VERTICAL` creates a vertical scrollbar.

#### 2) Scrollbar value range (min / max)
Scrollbar has a **minimum** and a **maximum**.

In AWT, `Scrollbar` also has a concept of **visible amount** (thumb size). This affects effective max.

Enhanced version uses:
- `new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, 201)`

Meaning (conceptually):
- **Orientation**: vertical
- **Initial value**: `0`
- **Visible amount**: `1` (acts like step size / thumb size)
- **Minimum**: `0`
- **Maximum**: `201` so the reachable values are effectively `0..200`

Why `201`?
- In AWT, the max you can reach is typically `(maximum - visibleAmount)`.
- Setting maximum to `201` and visible amount to `1` makes the last reachable value `200`.

#### 3) `AdjustmentListener` and `AdjustmentEvent`
When the scrollbar value changes, AWT fires an `AdjustmentEvent`.

You register the listener:
- `scrollbar.addAdjustmentListener(this);`

And implement the callback:
- `adjustmentValueChanged(AdjustmentEvent e)`

Inside the callback:
- `e.getValue()` returns the current scrollbar position (the chosen integer).

#### 4) Updating UI in response to the event
Typical updates are:
- Changing the title: `setTitle("Vertical Scroll = " + value)`
- Updating a label text: `valueLabel.setText("Value: " + value)`
- Triggering re-draw: `preview.setValue(value)` which calls `repaint()`

### UI arrangement (enhanced version)
Uses `BorderLayout`:
- `CENTER`: preview canvas (inside a `Panel`)
- `EAST`: scrollbar
- `SOUTH`: current value label

This makes the UI look cleaner and more “standard”.

### Window closing (important)
AWT does not automatically exit/cleanup just because you click the close button.

Enhanced version adds:
- A `WindowAdapter` and `windowClosing(...)` to call `dispose()`

That closes the window properly.

### Running on the correct thread (important concept)
GUI work should run on the **AWT Event Dispatch Thread (EDT)**.

Enhanced version does:
- `EventQueue.invokeLater(() -> new ScrollBarExample().setVisible(true));`

This is a best practice for AWT/Swing GUIs.

---

## Program 2: `CheckBoxExample.java` (Checkbox + ItemListener)

### What this program does
It creates an AWT window titled “Favorite Foods” with:
- 3 checkboxes (Dosa, Parotta, Fried Rice)
- 3 labels (initially empty)

When the user checks/unchecks a checkbox:
- If checked → the corresponding label shows that checkbox text
- If unchecked → the corresponding label becomes empty

### Controls/components used (and why)
- **`Frame`**: main window.
- **`Checkbox`**: a toggle control (on/off).
- **`Label`**: displays text output.
- **`FlowLayout`**: places components in a simple left-to-right order.

### Core event concept: `ItemListener`
Checkbox toggling is an **item state change**.

You register the listener:
- `c1.addItemListener(this);` (same for `c2`, `c3`)

AWT fires an `ItemEvent` when selection state changes.
Callback:
- `itemStateChanged(ItemEvent e)`

### How the program decides what to show
It checks each checkbox state:
- `c1.getState()` → `true` if selected, `false` if not selected.

Then updates the labels:
- If selected: `l1.setText(c1.getLabel());`
- Else: `l1.setText("");`

This is repeated for each checkbox (`c2/l2`, `c3/l3`).

### Key concept: checkbox label vs output label
- `Checkbox` has its own text (label) like `"Dosa"`.
- The program uses separate `Label` components (`l1`, `l2`, `l3`) to display the selection result.

This is a common GUI pattern: **input control** + **output/display control**.

### Extra concept: Checkboxes vs Radio Buttons
AWT `Checkbox` can behave like:
- **Independent checkboxes** (current program) — multiple can be selected.
- **Radio buttons** — if you use `CheckboxGroup`, only one can be selected at a time.

So:
- Use plain `Checkbox` for “select any/all”.
- Use `CheckboxGroup` for “select exactly one”.

---

## Controls Summary (Quick Reference)

### `Frame`
Top-level window. Typical usage:
- `setSize(w, h)`
- `setTitle("...")`
- `setLayout(new FlowLayout())` / `setLayout(new BorderLayout())`
- `setVisible(true)`

### `Scrollbar`
Used to select a numeric value by scrolling.
Common properties:
- Orientation: vertical/horizontal
- Value: current position
- Minimum / maximum range

### `Checkbox`
On/off selection control.
Main methods:
- `getState()` → checked or not
- `setState(true/false)`
- `getLabel()` → the text shown next to checkbox

### `Label`
Displays text output.
Main method:
- `setText("...")`

---

## Compile and Run (Windows / PowerShell)

Compile:
```bash
javac ScrollBarExample.java CheckBoxExample.java
```

Run:
```bash
java ScrollBarExample
java CheckBoxExample
```

---

## Common Notes / Viva Points

- AWT is **event-driven**; listeners react to user actions.
- `AdjustmentListener` is for continuous value changes (scrollbar).
- `ItemListener` is for state changes (checkbox selection).
- Layout managers control positioning; avoid manual positioning unless required.
- Prefer `setVisible(true)` over deprecated `show()`.
- Handling window closing is important for clean exit/cleanup (`dispose()` / `System.exit(0)` depending on need).
- Prefer creating GUI on the AWT EDT (`EventQueue.invokeLater`).

