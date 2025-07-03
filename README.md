# TP : Application de gestion tâches

## 🎯 Objectif pédagogique

Évaluer la capacité à créer des **pages HTML dynamiques avec Thymeleaf**, basées sur un modèle de données simple et des contrôleurs Spring MVC.

Pas de base de données, pas d’API REST : tout est **en mémoire**.

---

## 🌟 Contexte

Vous devez créer une petite application de gestion de projets et de tâches.

L’objectif est de pouvoir :

- Visualiser les utilisateurs
- Visualiser les projets et les tâches
- Créer des projets et des tâches
- Changer le statut des tâches

Le tout affiché dans **des pages HTML Thymeleaf**.

---

## 📚 Modèle de données à implémenter

```java
public enum TaskStatus {
    TODO, IN_PROGRESS, DONE;
}

public class User {
    private Long id;
    private String username;
    // Getters / Setters
}

public class Project {
    private Long id;
    private String name;
    private User creator;
    private List<Task> tasks = new ArrayList<>();
    // Getters / Setters
}

public class Task {
    private Long id;
    private String title;
    private TaskStatus status;
    private User assignee;
    // Getters / Setters
}

```

---

## 🔨 Fonctionnalités attendues (routes et vues)

### 🔹 Accueil (`/`)

- Afficher un menu vers :
    - Liste des utilisateurs
    - Liste des projets
    - Création d’un projet
    - Création d’une tâche

---

### 🔹 Liste des utilisateurs (`/users`)

- Afficher sous forme de tableau :
    - ID
    - Username

---

### 🔹 Liste des projets (`/projects`)

- Afficher sous forme de tableau :
    - ID
    - Nom du projet
    - Nom du créateur
    - Nombre de tâches
    - Lien vers la page de détail du projet

---

### 🔹 Détails d’un projet (`/projects/{id}`)

- Afficher :
    - Nom du projet
    - Nom du créateur
    - Liste des tâches associées :
        - Titre
        - Statut
        - Nom du responsable
        - Bouton pour passer au statut suivant

---

### 🔹 Création d’un projet (`/projects/create`)

- Formulaire pour saisir :
    - Nom du projet
    - ID du créateur
- Validation simple (champs obligatoires)

---

### 🔹 Création d’une tâche (`/tasks/create`)

- Formulaire pour saisir :
    - Titre
    - ID du projet
    - ID de l’utilisateur assigné
    - Statut initial (`TODO`)

---

### 🔹 Création d'un utilisateur (`/users/create`)

- Formulaire pour saisir :
    - Username

---

## 🛠️ Contraintes techniques

- Utiliser **Thymeleaf** pour toutes les pages.
- Pas de base de données : utilisez des **listes statiques en mémoire dans les services**.
- Organiser le projet en plusieurs packages : `model`, `service`, `controller`.

---

## 🎨 Exigences sur Thymeleaf

- Utiliser des fragments (`header`, `footer`) pour le layout général.
- Utiliser correctement :
    - `th:each`
    - `th:text`, `th:href`
    - `@{}` pour les liens internes
- Afficher proprement les erreurs dans les formulaires.