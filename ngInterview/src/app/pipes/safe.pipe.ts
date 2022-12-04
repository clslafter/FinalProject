import { Pipe, PipeTransform, SecurityContext } from '@angular/core';
import { DomSanitizer} from '@angular/platform-browser';

@Pipe({ name: 'safe' })
export class SafePipe implements PipeTransform {
  constructor(private domSanitizer: DomSanitizer) {}
  transform(url: any) {
    // return this.domSanitizer.sanitize(SecurityContext.URL, url);
    this.domSanitizer.bypassSecurityTrustResourceUrl(url);
  }
}
