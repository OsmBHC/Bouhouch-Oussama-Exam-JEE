import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { CreditDTO } from '../models/credit-dto';
import { CreditService } from '../services/credit.service';

@Component({
  selector: 'app-credits',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './credits.component.html',
  styleUrls: ['./credits.component.css']
})
export class CreditsComponent implements OnInit {
  credits: CreditDTO[] = [];
  page: number = 0;
  size: number = 10;
  clientId: number | null = null;

  constructor(private creditService: CreditService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const clientIdParam = params.get('clientId');
      this.clientId = clientIdParam ? +clientIdParam : null;
      this.loadCredits();
    });
  }

  loadCredits(): void {
    if (this.clientId !== null) {
      this.creditService.getCreditsByClientId(this.clientId).subscribe({
        next: (credits) => this.credits = credits,
        error: (err) => console.error('Error loading client credits:', err)
      });
    } else {
      this.creditService.getCredits(this.page, this.size).subscribe({
        next: (credits) => this.credits = credits,
        error: (err) => console.error('Error loading credits:', err)
      });
    }
  }

  deleteCredit(id: number | undefined): void {
    if (id === undefined) {
      console.error('Credit ID is undefined');
      alert('Cannot delete credit: ID is missing.');
      return;
    }
    if (confirm('Are you sure you want to delete this credit?')) {
      this.creditService.deleteCredit(id).subscribe({
        next: () => {
          this.credits = this.credits.filter(credit => credit.id !== id);
          alert('Credit deleted successfully.');
        },
        error: (err) => console.error('Error deleting credit:', err)
      });
    }
  }
}