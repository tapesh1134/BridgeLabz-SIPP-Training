
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

class contact {

    String name;
    String email;
    String phone;
    String address;

    public contact(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String info() {
        String res = name + " " + phone + " " + " " + email + " " + address;
        return res;
    }
}

class addressBook<T extends contact> {

    private HashMap<String, T> contacts = new HashMap<>();

    public void addContact(T contact) {
        contacts.put(contact.name, contact);
    }

    public void removeContact(String name) {
        contacts.remove(name);
    }

    public T searchByName(String name) {
        return contacts.get(name);
    }

    public T searchByPhone(String phone) {
        for (T c : contacts.values()) {

            if (c.phone.toLowerCase().equals(c.phone.toLowerCase())) {
                return c;
            }
        }
        return null;
    }

    public void listContacts() {
        for (T c : contacts.values()) {
            System.out.println(c);
        }
    }

    public List<T> sortByPhone(Comparator<T> comparator) {
        List<T> list = new ArrayList<>(contacts.values());
        list.sort(comparator);
        return list;
    }

    public boolean hasSameAddress(T c1, T c2) {
        return c1.address.equals(c2.address);
    }
}

class NameComparator implements Comparator<contact> {

    public int compare(contact a, contact b) {
        return a.name.compareTo(b.name);
    }
}

class PhoneComparator implements Comparator<contact> {

    public int compare(contact a, contact b) {
        return a.phone.compareTo(b.phone);
    }
}

public class Assest {

    public static void main(String[] args) {
        addressBook<contact> book = new addressBook();
        book.addContact(new contact("Ram", "ram@gamil.com", "56789333", "ABC"));
        book.addContact(new contact("Rohan", "Rohan@gamil.com", "43589333", "fgh"));
        book.addContact(new contact("Mohan", "m@gamil.com", "56786333", "wrc"));
        book.listContacts();
        for (contact c : book.sortByPhone(new PhoneComparator())) {
            System.out.println(c);
        }
    }
}
