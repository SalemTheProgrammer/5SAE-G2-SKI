import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SubscriptionService } from '../service/subscription-service.service';

@Component({
  selector: 'app-subscription',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css'],
})
export class SubscriptionComponent {
  subscriptions: any[] = [];
  subscription = { typeSub: '', startDate: '', endDate: '' };
  isUpdating = false;
  currentSubscriptionId: number | null = null;
  typeSub: string = 'ANNUAL';

  constructor(private subscriptionService: SubscriptionService) {}

  ngOnInit(): void {
    this.getAllSubscriptions();
  }

  getAllSubscriptions(): void {
    this.subscriptionService.getSubscriptionsByType(this.typeSub).subscribe((data) => {
      this.subscriptions = Array.from(data);
    });
  }

  onSubmit(): void {
    if (this.isUpdating && this.currentSubscriptionId) {
      this.subscriptionService
        .updateSubscription({ ...this.subscription, id: this.currentSubscriptionId })
        .subscribe(() => {
          this.resetForm();
          this.getAllSubscriptions();
        });
    } else {
      this.subscriptionService.addSubscription(this.subscription).subscribe(() => {
        this.resetForm();
        this.getAllSubscriptions();
      });
    }
  }

  editSubscription(subscription: any): void {
    this.isUpdating = true;
    this.currentSubscriptionId = subscription.id;
    this.subscription = { ...subscription };
  }

  resetForm(): void {
    this.subscription = { typeSub: '', startDate: '', endDate: '' };
    this.isUpdating = false;
    this.currentSubscriptionId = null;
  }
}
