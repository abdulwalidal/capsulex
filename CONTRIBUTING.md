# Contributing Guidelines

Thank you for contributing to **CapsuleX**. This document details the contribution standards, development workflow, and pull request submission process for this repository.

---

## Code of Conduct

All contributors and maintainers are expected to adhere to the [Code of Conduct](CODE_OF_CONDUCT.md). Please report any unacceptable behavior to the project maintainer.

---

## Development Workflow

### Branching Model

This project follows a trunk-based pull request model. The `main` branch is protected and reflects stable, production-ready code.

Direct commits to `main` are rejected. All work must follow this sequence:

1. **Fork the Repository:** Create a personal fork of `abdulwalidal/capsulex` on GitHub.
2. **Clone Locally:**
   ```bash
   git clone git@github.com:<your-username>/capsulex.git
   cd capsulex
   ```
3. **Create a Feature Branch:** Branch off the latest `main` branch using a clear, prefixed branch name:
   ```bash
   git checkout -b feature/<short-description>
   # Example:
   git checkout -b feature/battery-safety-check
   # For bug fixes:
   git checkout -b fix/<short-description>
   # For documentation:
   git checkout -b docs/<short-description>
   ```
4. **Implement and Test Changes:** Write clean, modular code with accompanying unit tests.
5. **Format and Verify Locally:**
   ```bash
   npm run lint
   npm test
   ```
6. **Commit Changes:** Use structured commit messages adhering to the Conventional Commits specification.
7. **Submit a Pull Request:** Push the branch to your fork and open a pull request targeting `main`.

---

## Commit Message Conventions

Commit messages must be concise, descriptive, and follow the Conventional Commits specification:

```text
<type>(<scope>): <short description>
```

### Accepted Types:
- `feat`: A new feature or capability.
- `fix`: A bug fix.
- `docs`: Documentation updates.
- `style`: Formatting changes that do not affect runtime behavior.
- `refactor`: Code restructuring without adding features or fixing bugs.
- `test`: Adding or modifying tests.
- `chore`: Maintenance tasks, dependency updates, or build configuration.

### Examples:
- `feat(hal): implement read-only linux sysfs power supply watcher`
- `fix(safety): enforce 50 percent minimum battery threshold`
- `docs(arch): add threat model matrix for capsule staging`

---

## Pull Request Guidelines

Before submitting your pull request, verify that:
- [ ] Your code adheres to project formatting and linting rules.
- [ ] All new logic includes unit or simulation tests.
- [ ] No direct physical flash writing code is introduced (strictly respect the Zero-Brick policy).
- [ ] You have rebased against the latest `main` branch to avoid merge conflicts.
- [ ] The pull request description links any relevant issues and explains the motivation.
