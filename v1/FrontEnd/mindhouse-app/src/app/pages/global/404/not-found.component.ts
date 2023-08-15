import { Component, HostListener } from '@angular/core';

/**
 * NotFoundComponent with the component configuration.
 */
@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.scss']
})
export class NotFoundComponent {

  /**
   * Animation for the background using the mouse.
   *
   * @param {MouseEvent} event - The MouseEvent object received from the `mousemove` event.
   * @returns {void}
   */
  @HostListener('mousemove', ['$event'])
  onMousemove(event: MouseEvent) {
    var container = document.getElementsByClassName("container")[0] as HTMLElement;
    const x = -event.clientX / 5;
    const y = -event.clientY / 5;
    container.style.backgroundPositionX = x + 'px';
    container.style.backgroundPositionY = y + 'px';
  }
}
