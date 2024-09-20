
import com.models.Client;
import com.services.ClientService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClientService clientService;

        try {
            clientService = new ClientService();
        } catch (SQLException e) {
            System.err.println("Failed to initialize the client service: " + e.getMessage());
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
                    handleClientSearchOrAdd(scanner, clientService);
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

    private static void handleClientSearchOrAdd(Scanner scanner, ClientService clientService) {
        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                searchExistingClient(scanner, clientService);
                break;
            case 2:
                addNewClient(scanner, clientService);
                break;
            default:
                System.out.println("Option invalide. Veuillez réessayer.");
        }
    }

    private static void searchExistingClient(Scanner scanner, ClientService clientService) {
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
                System.out.println("Numéro de téléphone : " + foundClient.getPhone());

                System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
                char continueWithClient = scanner.next().charAt(0);
                scanner.nextLine(); // Consume newline
                if (continueWithClient == 'y' || continueWithClient == 'Y') {
                    // Logic to proceed with the project creation (to be implemented)
                    System.out.println("Proceeding with project creation...");
                } else {
                    // Proceed to create a new client
                    addNewClient(scanner, clientService);
                }
            } else {
                System.out.println("Client non trouvé.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du client : " + e.getMessage());
        }
    }


    private static void addNewClient(Scanner scanner, ClientService clientService) {
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
            clientService.createClient(newClient);
            System.out.println("Client ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du client : " + e.getMessage());
        }
    }
}
