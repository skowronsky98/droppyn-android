package com.droppyn.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.droppyn.R
import com.droppyn.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

  private lateinit var chatViewModel: ChatViewModel
  private lateinit var binding: FragmentChatBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    chatViewModel =
            ViewModelProvider(this).get(ChatViewModel::class.java)
    binding = FragmentChatBinding.inflate(layoutInflater, container, false)
    binding.chatViewModel = chatViewModel

    return binding.root
  }
}