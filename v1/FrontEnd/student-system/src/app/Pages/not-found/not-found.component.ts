import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.css']
})
export class NotFoundComponent {


  @HostListener('mousemove', ['$event'])
  onMousemove(event: MouseEvent) {
    var container = document.getElementsByClassName("container")[0] as HTMLElement;
    const x = -event.clientX / 5;
    const y = -event.clientY / 5;
    container.style.backgroundPositionX = x + 'px';
    container.style.backgroundPositionY = y + 'px';
  }
}
