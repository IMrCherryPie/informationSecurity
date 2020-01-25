import vvv.DataPacket;
import vvv.DocumentRights;
import vvv.User;

import java.util.ArrayList;
import java.util.Scanner;

    public class Main {
        private static ArrayList<User> USERS = new ArrayList<User>();
        private static ArrayList<DataPacket> DATA_PACKETS = new ArrayList<DataPacket>();
        private static User SESSION = null;

        public static void main(String[] args) {

            // Создаём пользователей
            CreateUsers();
            // Создаём документа к которым пользователи будут обращаться
            CreateDataPakets();
            //Генерируем количество привилегий на 1го пользователя

            generateRandomRightsOfUser();
            //Цикл общения с пользователем
            /*for (User user: USERS) {
                print(user);
            }*/
            interfaceForMan();
        }

        private static void interfaceForMan() {
            while (true) {
                System.out.println("Придётся залогиниться");
                authentication();
                print(SESSION);
                boolean exit = true;
                while (exit) {
                    Scanner in = new Scanner(System.in);
                    System.out.println("Что желаете?\n" +
                            "1) Введите цифру \"1\" для чтение документа\n" +
                            "2) Введите цивфру \"2\" для редактирование документа\n" +
                            "3) Введмте циру \"3\" для передачи прав другому пользователю\n" +
                            "4) Введите цифру \"4\" для выхода"
                    );
                    System.out.println();
                    int choice = in.nextInt();

                    switch (choice) {
                        case 1: {
                            System.out.println("\nВведите название документа:\n");
                            in = new Scanner(System.in);
                            String str = in.nextLine();
                            if (readRightsCheck(SESSION, str))
                                System.out.println("Исполнено");
                            System.out.println("Отказано в доступе или такого документа нет!");

                            break;
                        }
                        case 2: {
                            System.out.println("Вы выбрали запись (изменение документа)" +
                                    "\n Введите название документа\n");
                            in = new Scanner(System.in);
                            String str = in.nextLine();
                            if (writeRightsCheck(SESSION, str))
                                System.out.println("Выполнено редактирование документа");
                            System.out.println("Отказано в доступе или такого документа нет!");
                            break;
                        }
                        case 3: {
                            System.out.println("Вы выбрали передача прав дургому пользователю");
                            System.out.println("Введите НАЗВАНИЕ ДОКУМЕНТА, права на которого ВЫ хотите передать другому пользователю.\n" +
                                    "Обратите внимание на то что прва передаются толко положительные");
                            transferOfRights();
                            break;
                        }
                        case 4: {
                            exit = false;
                            SESSION = null;
                            break;
                        }
                        default:
                            System.out.println("Неверный ввод");
                    }
                }
            }
        }

        private static void generateRandomRightsOfUser() {
            for (User user : USERS) {
                for (DocumentRights docRig : user.getArrayListDocumentRights()) {
                    int i = (int) (Math.random() * 3);
                    for (int j = 0; j < i; j++) {
                        switch ((int) (Math.random() * 3)) {
                            case 0: {
                                docRig.getOpportunitiesOfUser().setOnlyRead(true);
                                break;
                            }
                            case 1: {
                                docRig.getOpportunitiesOfUser().setReadWrite(true);
                                break;
                            }
                            case 2: {
                                docRig.getOpportunitiesOfUser().setTransferOfRights(true);
                                break;
                            }
                            default:
                                System.out.println("Ошибка в генерации прав пользователей------------------------------------------------------");
                        }
                    }
                }
            }
        }

        private static void transferOfRights() {
            Scanner in = new Scanner(System.in);
            String str = in.nextLine();
            for (DocumentRights documentRights : SESSION.getArrayListDocumentRights()) {
                if (documentRights.getName().equals(str)) {
                    if (documentRights.getOpportunitiesOfUser().isFullRights() ||
                            documentRights.getOpportunitiesOfUser().isTransferOfRights()) {
                        System.out.println("Введите ИМЯ ПОЛЬЗОВАТЕЛЯ НАЗНАЧЕНИЯ прав.\n");
                        in = new Scanner(System.in);
                        str = in.nextLine();
                        for (User user : USERS) {
                            if (user.getName().equals(str)) {
                                for (DocumentRights docRigUser : user.getArrayListDocumentRights()) { //Ищем доекмент на который необходимо передать права
                                    if (docRigUser.getName().equals(documentRights.getName()))
                                        if (documentRights.getOpportunitiesOfUser().isFullRights())
                                            docRigUser.getOpportunitiesOfUser().setFullRights(true);
                                    if (documentRights.getOpportunitiesOfUser().isTransferOfRights())
                                        docRigUser.getOpportunitiesOfUser().setTransferOfRights(true);
                                    if (documentRights.getOpportunitiesOfUser().isReadWrite())
                                        docRigUser.getOpportunitiesOfUser().setReadWrite(true);
                                    if (documentRights.getOpportunitiesOfUser().isOnlyRead())
                                        docRigUser.getOpportunitiesOfUser().setOnlyRead(true);
                                    break;
                                }
                            }
                        }
                        System.out.println("Права переданы");
                        return;
                    } else {
                        System.out.println("У вас нет прав! Прочь, Безправный!");
                        return;
                    }
                }
            }
            System.out.println("Документ с заданным именем - \"" + str + "\" отсутвует в базе");
        }

        private static void CreateDataPakets() {
            DATA_PACKETS.add(new DataPacket("Bible", 2020, USERS, DATA_PACKETS.size()));
            DATA_PACKETS.add(new DataPacket("Philosophy", 1999, USERS, DATA_PACKETS.size()));
            DATA_PACKETS.add(new DataPacket("Constitution", 1, USERS, DATA_PACKETS.size()));
            DATA_PACKETS.add(new DataPacket("Russian history", 2021, USERS, DATA_PACKETS.size()));
            DATA_PACKETS.add(new DataPacket("European history", 2019, USERS, DATA_PACKETS.size()));
        }

        private static void CreateUsers() {
            USERS.add(new User("Alexei", "qwerty1", true));
            USERS.add(new User("Evgeniy", "qwerty2"));
            USERS.add(new User("Tatyana", "qwerty3"));
            USERS.add(new User("Eldar", "qwerty4"));
            USERS.add(new User("Yefim", "qwerty5"));
        }

        public static void print(User user) {
            System.out.println("Имя пользоваьеля: " + user.getName() +
                    "\nПеречень ваших прав:");
            for (DocumentRights documentRights : user.getArrayListDocumentRights()) {
                System.out.println("\tName document: " + DATA_PACKETS.get(documentRights.getIdDataPacket()).getName());
                System.out.println("\t\tПрава: " + documentRights.getOpportunitiesOfUser().getTrueRights());
            }
        }

        public static void authentication() {
            while (true) {
                Scanner in = new Scanner(System.in);
                System.out.println("Введите логин");
                String name = in.nextLine();
                System.out.println("Введите пароль");
                String password = in.nextLine();
                for (User user : USERS) {
                    if (user.getName().equals(name) && user.getAuthenticationOfUser().getPassword().equals(password)) {
                        SESSION = user;
                        return;
                    }
                }
            }
        }

        public static boolean readRightsCheck(User user, int idDoc) {
            return (user.getArrayListDocumentRights().get(idDoc).getOpportunitiesOfUser().isOnlyRead() ||
                    user.getArrayListDocumentRights().get(idDoc).getOpportunitiesOfUser().isFullRights());
        }

        public static boolean readRightsCheck(User user, String nameDoc) {
            for (DocumentRights documentRights : user.getArrayListDocumentRights()) {
                if (documentRights.getName().equals(nameDoc)) {
                    return documentRights.getOpportunitiesOfUser().isFullRights() ||
                            documentRights.getOpportunitiesOfUser().isOnlyRead();
                }
            }
            return false;
        }

        public static boolean writeRightsCheck(User user, int idDoc) {
            return (user.getArrayListDocumentRights().get(idDoc).getOpportunitiesOfUser().isReadWrite() ||
                    user.getArrayListDocumentRights().get(idDoc).getOpportunitiesOfUser().isFullRights());
        }

        public static boolean writeRightsCheck(User user, String nameDoc) {
            for (DocumentRights documentRights : user.getArrayListDocumentRights()) {
                if (documentRights.getName().equals(nameDoc)) {
                    return documentRights.getOpportunitiesOfUser().isReadWrite() ||
                            documentRights.getOpportunitiesOfUser().isOnlyRead();
                }
            }
            return false;
        }
    }