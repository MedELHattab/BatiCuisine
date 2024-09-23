import com.models.*;
import com.services.ClientService;
import com.services.ProjectService;
import com.services.ComponentService;
import java.util.Locale;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        ClientService clientService;
        ProjectService projectService;
        ComponentService componentService;

        try {
            clientService = new ClientService();
            projectService = new ProjectService();
            componentService = new ComponentService();
        } catch (SQLException e) {
            System.err.println("Failed to initialize services: " + e.getMessage());
            return;
        }

        while (true) {
            System.out.println("=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===");
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Calculer le coût d'un projet");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    handleClientSearchOrAdd(scanner, clientService, projectService, componentService);
                    break;

                case 2:
                    // Logic to display existing projects (to be implemented)
                    System.out.println("Fonctionnalité non implémentée pour afficher les projets existants.");
                    break;

                case 3:
                    // Logic to calculate project cost (to be implemented)
                    System.out.println("Fonctionnalité non implémentée pour calculer le coût d'un projet.");
                    break;

                case 4:
                    // Quit
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;

                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private static void handleClientSearchOrAdd(Scanner scanner, ClientService clientService, ProjectService projectService, ComponentService componentService) {
        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        int clientId = 0; // Initialize clientId to 0

        switch (choice) {
            case 1:
                Client existingClient = searchExistingClient(scanner, clientService); // Search for an existing client
                if (existingClient != null) {
                    clientId = existingClient.getClientId(); // Get the ID of the existing client
                } else {
                    System.out.println("Aucun client trouvé.");
                    return; // Exit if no client is found
                }
                break;

            case 2:
                clientId = addNewClient(scanner, clientService); // Add a new client and get the ID
                if (clientId <= 0) {
                    System.out.println("Erreur lors de l'ajout du client. Veuillez réessayer.");
                    return; // Exit if there was an error adding the client
                }
                break;

            default:
                System.out.println("Option invalide. Veuillez réessayer.");
                return;
        }

        // Continue with project creation using the client ID
        handleProjectCreation(scanner, projectService, componentService, clientId);
    }

    private static Client searchExistingClient(Scanner scanner, ClientService clientService) {
        System.out.println("--- Recherche de client existant ---");
        System.out.print("Entrez le nom du client : ");
        String clientName = scanner.nextLine();

        try {
            List<Client> clients = clientService.getAllClients();
            Client foundClient = clients.stream()
                    .filter(client -> client.getName().equalsIgnoreCase(clientName))
                    .findFirst()
                    .orElse(null);

            if (foundClient != null) {
                System.out.println("Client trouvé !");
                System.out.println("Nom : " + foundClient.getName());
                System.out.println("Adresse : " + foundClient.getAddress());
                System.out.println("Numéro de téléphone : " + foundClient.getPhoneNumber());

                System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
                char continueWithClient = scanner.next().charAt(0);
                scanner.nextLine(); // Consume newline
                if (continueWithClient == 'y' || continueWithClient == 'Y') {
                    return foundClient; // Return the found client if the user wants to continue
                }
            } else {
                System.out.println("Client non trouvé.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du client : " + e.getMessage());
        }

        return null; // Return null if no client was found or an error occurred
    }

    private static int addNewClient(Scanner scanner, ClientService clientService) {
        System.out.println("--- Ajout d'un Nouveau Client ---");
        System.out.print("Entrez le nom du client : ");
        String name = scanner.nextLine();
        System.out.print("Entrez l'adresse du client : ");
        String address = scanner.nextLine();
        System.out.print("Entrez le numéro de téléphone du client : ");
        String phone = scanner.nextLine();
        System.out.print("Est-ce un professionnel ? (true/false) : ");
        boolean isProfessional = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        Client newClient = new Client(0, name, address, phone, isProfessional);
        try {
            int clientId = clientService.createClient(newClient); // This should return the new client's ID
            System.out.println("Client ajouté avec succès avec l'ID : " + clientId);
            return clientId; // Return the new client ID
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du client : " + e.getMessage());
            return -1; // Return -1 if there's an error
        }
    }



    private static void handleProjectCreation(Scanner scanner, ProjectService projectService, ComponentService componentService, int clientId) {
        System.out.println("--- Création d'un Nouveau Projet ---");
        System.out.print("Entrez le nom du projet : ");
        String projectName = scanner.nextLine();
        System.out.print("Entrez la surface du projet (en m²) : ");
        double surfaceArea = scanner.nextDouble();
        System.out.print("Entrez la marge bénéficiaire (%) : ");
        double profitMargin = scanner.nextDouble();

        // Create the new Project object
        Project newProject = new Project(0, projectName, surfaceArea, profitMargin, 0.0, ProjectStatus.Ongoing, clientId);

        try {
            // Create the project and retrieve the generated projectId
            int projectId = projectService.createProject(newProject);
            System.out.println("Projet créé avec succès avec l'ID: " + projectId);

            // Add components to the newly created project using projectId
            addComponents(scanner, componentService, projectId);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création du projet : " + e.getMessage());
        }
    }



    private static void addComponents(Scanner scanner, ComponentService componentService, int projectId) {
        boolean addingComponents = true;

        while (addingComponents) {
            System.out.println("--- Ajout de composants ---");
            System.out.print("Souhaitez-vous ajouter un matériau ou une main-d'œuvre ? (1: Matériau, 2: Main-d'œuvre) : ");
            int componentType = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                if (componentType == 1) {
                    addMaterial(scanner, componentService, projectId);
                } else if (componentType == 2) {
                    addLabor(scanner, componentService, projectId);
                } else {
                    System.out.println("Option invalide.");
                }

                System.out.print("Voulez-vous ajouter un autre composant ? (y/n) : ");
                char continueAdding = scanner.next().charAt(0);
                scanner.nextLine(); // Consume newline
                addingComponents = (continueAdding == 'y' || continueAdding == 'Y');
            } catch (SQLException e) {
                System.err.println("Erreur lors de l'ajout du composant : " + e.getMessage());
            }
        }
    }

    private static void addMaterial(Scanner scanner, ComponentService componentService, int projectId) throws SQLException {
        System.out.print("Entrez le nom du matériau : ");
        String materialName = scanner.nextLine();
        System.out.print("Entrez la quantité : ");
        double quantity = scanner.nextDouble();
        System.out.print("Entrez le coût unitaire : ");
        double unitCost = scanner.nextDouble();
        System.out.print("Entrez le coût de transport : ");
        double transportCost = scanner.nextDouble();
        System.out.print("Entrez le coefficient de qualité : ");
        double qualityCoefficient = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Material material = new Material(0, materialName, quantity, unitCost, transportCost, qualityCoefficient);
        componentService.addComponent(material, projectId);
        System.out.println("Matériau ajouté avec succès !");
    }


    private static void addLabor(Scanner scanner, ComponentService componentService, int projectId) throws SQLException {
        System.out.print("Entrez le type de main-d'œuvre : ");
        String laborType = scanner.nextLine();
        System.out.print("Entrez le taux horaire : ");
        double hourlyRate = scanner.nextDouble();
        System.out.print("Entrez le nombre d'heures travaillées : ");
        double workHours = scanner.nextDouble();
        System.out.print("Entrez le facteur de productivité : ");
        double productivityFactor = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Labor labor = new Labor(0, laborType, laborType, hourlyRate, workHours, productivityFactor);
        componentService.addComponent(labor, projectId);
        System.out.println("Main-d'œuvre ajoutée avec succès !");
    }

}
