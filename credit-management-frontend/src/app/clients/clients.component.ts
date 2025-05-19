import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ClientDTO } from '../models/client-dto';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-clients',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {
  clients: ClientDTO[] = [];
  searchKeyword: string = '';

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.clientService.getClients().subscribe({
      next: (clients) => this.clients = clients,
      error: (err) => console.error('Error loading clients:', err)
    });
  }

  searchClients(): void {
    if (this.searchKeyword.trim()) {
      this.clientService.searchClients(this.searchKeyword).subscribe({
        next: (clients) => this.clients = clients,
        error: (err) => console.error('Error searching clients:', err)
      });
    } else {
      this.loadClients();
    }
  }

  deleteClient(id: number | undefined): void {
    if (id === undefined) {
      console.error('Client ID is undefined');
      alert('Cannot delete client: ID is missing.');
      return;
    }
    if (confirm('Are you sure you want to delete this client?')) {
      this.clientService.deleteClient(id).subscribe({
        next: () => {
          this.clients = this.clients.filter(client => client.id !== id);
          alert('Client deleted successfully.');
        },
        error: (err) => console.error('Error deleting client:', err)
      });
    }
  }
}