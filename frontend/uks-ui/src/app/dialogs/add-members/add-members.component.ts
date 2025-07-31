import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-members',
  templateUrl: './add-members.component.html',
  styleUrls: ['./add-members.component.css'],
})
export class AddMembersComponent {
  usernames: string[] = ['alice', 'bob', 'charlie', 'diana', 'eve', 'frank']; // All available members
  searchControl = new FormControl('');
  filteredUsernames: string[] = this.usernames;
  addedMembers: string[] = []; // Members in current organization

  constructor() {
    this.searchControl.valueChanges.subscribe((searchText) => {
      const query = searchText ?? ''; // Ensure `searchText` is not null
      this.filteredUsernames = this.usernames.filter((username) =>
        username.toLowerCase().includes(query.toLowerCase())
      );
    });
  }

  setSearch(username: string): void {
    this.searchControl.setValue(username); // Set the clicked username in the search bar
  }

  addMember(): void {
    const searchValue = this.searchControl.value?.trim() || ''; // Fallback to empty string if undefined
    if (searchValue === '') {
      alert('Please enter a valid username.');
      return;
    }
    if (this.usernames.includes(searchValue)) {
      if (!this.addedMembers.includes(searchValue)) {
        this.addedMembers.push(searchValue);
        alert(`${searchValue} added successfully!`);
      } else {
        alert(`${searchValue} is already added.`);
      }
    } else {
      alert(`${searchValue} does not exist.`);
    }
  }
}
