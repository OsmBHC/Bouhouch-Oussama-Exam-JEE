import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CreditDTO } from '../models/credit-dto';
import { CreditService } from '../services/credit.service';

@Component({
  selector: 'app-new-credit',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './new-credit.component.html',
  styleUrls: ['./new-credit.component.css']
})
export class NewCreditComponent {
  credit: CreditDTO = {
    status: 'PENDING',
    amount: 0,
    interestRate: 0,
    clientId: 0
  };

  constructor(private creditService: CreditService, private router: Router) {}

  saveCredit(): void {
    if (this.credit.amount <= 0 || this.credit.interestRate <= 0 || this.credit.clientId <= 0) {
      alert('Amount, interest rate, and client ID must be positive numbers.');
      return;
    }
    this.creditService.createCredit(this.credit).subscribe({
      next: () => {
        alert('Credit created successfully.');
        this.router.navigate(['/credits']);
      },
      error: (err) => {
        console.error('Error saving credit:', err);
        alert('Failed to create credit.');
      }
    });
  }
}