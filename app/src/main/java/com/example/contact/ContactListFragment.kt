package com.example.contact

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class ContactListFragment : Fragment(), MenuProvider {
    val contacts = mutableListOf<Contact>()
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_contact_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val names = listOf("Raonal do Doe", "Messi Lionel", "Neymar Jr", "Vanper Johnson", "Vangert Mount",
            "Charlie Brown", "Thomas Miller", "Frank Wilson", "Grace Davis", "Henry Kane")

        for (i in 1..10) {
            val name = names[i - 1]
            val phoneNumber = "999${i}888888"
            val email = "${name.lowercase().replace(" ", ".")}@example.com"
            val address = "Address_$i"

            val contact = Contact(i, "avatar$i", name, phoneNumber, email, address)
            contacts.add(contact)
        }

        val newName = arguments?.getString("new_name")
        val newPhoneNumber = arguments?.getString("new_phoneNumber")
        val newEmail = arguments?.getString("new_email")
        val newAddress = arguments?.getString("new_address")

        if (newName != null && newPhoneNumber != null && newEmail != null && newAddress != null) {
            val newContact = Contact(contacts.size + 1, "avatar", newName, newPhoneNumber, newEmail, newAddress)
            contacts.add(newContact)
        }
        val recyclerView: RecyclerView = view.findViewById(R.id.list_contact_rv)
        val contactAdapter = ContactAdapter(this, contacts)
        recyclerView.adapter = contactAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
            )
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
       // TODO("Not yet implemented")
        menuInflater.inflate(R.menu.action_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
      //  TODO("Not yet implemented")
        when(menuItem.itemId) {
            R.id.action_add -> {
                findNavController().navigate(R.id.action_contactListFragment_to_contactAddFragment)
            }
        else -> {
            //todo...
        }
        }
        return false
    }
}


