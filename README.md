# Pokedex Android Application

Ce projet est un Pokedex interactif développé pour améliorer mes compétences en développement Android. L'application utilise l'API [PokeAPI](https://pokeapi.co/) pour afficher les données des Pokémon, en exploitant l'architecture SOLID, l'injection de dépendance avec Koin, et Jetpack Compose pour l'interface utilisateur.

## Fonctionnalités

- **Affichage de la liste des Pokémon** : Affichage interactif des Pokémon avec leurs images et leur nom.
- **Recherche de Pokémon** : Rechercher un Pokémon spécifique par son nom.
- **Détails des Pokémon** : Accéder aux informations détaillées pour chaque Pokémon, telles que ses statistiques, ses attaques et ses évolutions.
- **Design adaptatif** : Interface optimisée pour différents formats d'écran grâce à Jetpack Compose.

## Technologies et outils

- **Langage** : Kotlin
- **Architecture** : Principes SOLID
- **DI (Injection de Dépendance)** : Koin
- **UI** : Jetpack Compose
- **API** : [PokeAPI](https://pokeapi.co/)
- **Gestion des appels API** : Retrofit
- **Gestion des données** : Repository pour l'abstraction des sources de données

## Prérequis

- **Android Studio** Ladybug ou version supérieure
- **Kotlin** 2.0.0
- **Connexion Internet** pour accéder aux données via PokeAPI

## Installation

1. Clone le dépôt :
   ```bash
   git clone https://github.com/votre-utilisateur/pokedex-android.git
   cd pokedex-android
   ```
2. Ouvrez le projet dans Android Studio.
3. Synchronisez les dépendances avec Gradle.
4. Compilez et exécutez l'application sur un appareil ou un émulateur Android.

## Architecture

Le projet est construit selon les principes SOLID pour garantir un code propre, maintenable et extensible.

### Principes SOLID

1. **Single Responsibility Principle (SRP)** : Chaque classe a une seule responsabilité. Par exemple, une classe `PokemonRepository` gère l'accès aux données de Pokémon, tandis qu'une classe `PokemonViewModel` s'occupe de la logique de présentation.
2. **Open/Closed Principle (OCP)** : Les classes sont ouvertes à l'extension mais fermées aux modifications. Par exemple, `PokemonRepository` implémente une interface, permettant d'étendre ou de modifier le comportement sans toucher à la classe elle-même.
3. **Liskov Substitution Principle (LSP)** : Chaque sous-classe peut être utilisée comme son parent sans casser le code. Par exemple, des classes concrètes de type `PokemonRepositoryImpl` peuvent remplacer `PokemonRepository`.
4. **Interface Segregation Principle (ISP)** : Les interfaces sont spécifiques et n'imposent pas d'implémenter des méthodes inutiles. Par exemple, `PokemonRepository` n'expose que les méthodes nécessaires pour les opérations liées aux Pokémon.
5. **Dependency Inversion Principle (DIP)** : Les classes dépendent d'abstractions plutôt que d'implémentations concrètes. Par exemple, `PokemonRepository` est injecté via une interface dans le ViewModel.

### Injection de Dépendance avec Koin

L'injection de dépendance est gérée par Koin pour une configuration simple et efficace.

```kotlin
// Exemple de module Koin
val appModule = module {
    single { Retrofit.Builder()...build() } // Instance Retrofit pour les appels API
    single { get<Retrofit>().create(PokemonApi::class.java) } // Interface API
    single<PokemonRepository> { PokemonRepositoryImpl(get()) } // Repository
    viewModel { PokemonViewModel(get()) } // ViewModel injecté avec le Repository
}
```
Dans `Application` :

```kotlin
class PokedexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokedexApplication)
            modules(appModule)
        }
    }
}
```

### Structure du projet

L'application suit une architecture en couches pour séparer les différentes responsabilités :

- **data** : Contient les sources de données (API, modèles de données) et le `Repository`.
- **domain** : Définit les interfaces, les modèles métiers, et les cas d'utilisation (si nécessaire).
- **ui** : Gère l'interface utilisateur et inclut les Composables Jetpack Compose, les ViewModels, et les activités.

## Utilisation

1. Au démarrage, l'application affiche une liste de Pokémon obtenue via PokeAPI.
2. L'utilisateur peut faire défiler la liste ou rechercher un Pokémon par nom.
3. En appuyant sur un Pokémon, l'utilisateur accède à ses détails, incluant des informations supplémentaires comme ses statistiques et évolutions.

## Dépendances principales

- **Jetpack Compose** : Pour un design moderne et réactif de l'application.
- **Koin** : Injection de dépendance légère et simple d'utilisation.
- **Retrofit** : Pour la gestion des appels réseau vers PokeAPI.
- **Coil** : Pour le chargement d'images, notamment les sprites des Pokémon.

## Améliorations futures

- **Mode hors ligne** : Caching des données pour une consultation hors connexion.
- **Tests Unitaires** : Ajouter des tests pour les ViewModels et les Repository afin d'assurer une meilleure couverture de code.

## Ressources

- [PokeAPI Documentation](https://pokeapi.co/docs/)
- [Koin Documentation](https://insert-koin.io/)
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
