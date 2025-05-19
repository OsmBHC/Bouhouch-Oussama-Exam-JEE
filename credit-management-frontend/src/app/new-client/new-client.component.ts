import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ClientDTO } from '../models/client-dto';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-new-client',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.css']
})
export class NewClientComponent {
  client: ClientDTO = { name: '', email: '' };

  constructor(private clientService: ClientService, private router: Router) {}

  saveClient(): void {
    if (!this.client.name.trim() || !this.client.email.trim()) {
      alert('Name and email are required.');
      return;
    }
    this.clientService.saveClient(this.client).subscribe({
      next: () => {
        alert('Client created successfully.');
        this.router.navigate(['/clients']);
      },
      error: (err) => {
        console.error('Error saving client:', err);
        alert('Failed to create client.');
      }
    });
  }
}